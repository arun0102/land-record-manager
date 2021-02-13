package arun.pkg.landrecorder

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import arun.pkg.landrecorder.ui.addrecord.AddRecordFragment
import arun.pkg.landrecorder.ui.chartview.ChartViewFragment
import arun.pkg.landrecorder.ui.main.MainFragment
import arun.pkg.landrecorder.ui.settings.SettingsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
        findViewById<NavigationView>(R.id.navigation).setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment = MainFragment.newInstance()
        when (item.itemId) {
            R.id.home -> {
                fragment = MainFragment.newInstance()
            }
            R.id.graph -> {
                fragment = ChartViewFragment.newInstance()
            }
            R.id.add_record -> {
                fragment = AddRecordFragment.newInstance()
            }
            R.id.settings -> {
                fragment = SettingsFragment.newInstance()
            }
        }
        findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawers()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }
}