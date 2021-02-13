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

package arun.pkg.landrecorder


import arun.pkg.landrecorder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Application class
 */
class LandRecorderApplication : DaggerApplication() {

    //Dagger lib initialization
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }
}