/*
 *
 *  * © 2020 Infosys Limited, Bangalore, India. All Rights Reserved.
 *  * Version:1.0.0.0
 *  *
 *  * Except for any free or open source software components embedded in this Infosys proprietary software program (“Program”),
 *  * this Program is protected by copyright laws, international treaties and other pending or existing intellectual property 
 *  * rights in India, the United States and other countries. Except as expressly permitted, any unauthorized reproduction, 
 *  * storage, transmission in any form or by any means (including without limitation electronic, mechanical, printing, photocopying, recording or otherwise),
 *  * or any distribution of this Program, or any portion of it, may result in severe civil and criminal penalties, and will be prosecuted to the maximum 
 *  * extent possible under the law.
 *  *
 *  
 */

package arun.pkg.landrecorder.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * [ViewModel] factory provider class for Dagger DI
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]
        if (null == creator) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        requireNotNull(creator) { "Unknown model class $modelClass" }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}