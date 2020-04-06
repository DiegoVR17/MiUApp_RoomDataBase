package com.diegovr17.miu_app_roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.diegovr17.miu_app_roomdatabase.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notasFragment: NotasFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(tool_bar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            val drawerToggle : ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer_layout,
                tool_bar,
                R.string.drawer_open,
                R.string.drawer_close
            ){
                override fun onDrawerClosed(drawerView: View){
                    super.onDrawerClosed(drawerView)
                    setTitle(R.string.app_name)
                }

                override fun onDrawerOpened(drawerView: View){
                    super.onDrawerClosed(drawerView)
                    setTitle(R.string.option)
                }

            }

            notasFragment = NotasFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment,
                notasFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()

            drawerToggle.isDrawerIndicatorEnabled = true
            drawer_layout.addDrawerListener(drawerToggle)
            drawerToggle.syncState()

            nav_view.setNavigationItemSelectedListener {
                item: MenuItem ->
                when(item.itemId){
                    R.id.misNotas ->{
                        loadNotas(NotasFragment())
                        true
                    }
                    R.id.miHorario ->{
                        loadHorario(HorarioFragment())
                        true
                    }
                    R.id.miCalendario ->{
                        loadCalendario(CalendarioFragment())
                        true
                    }
                    R.id.simulador ->{
                        loadSimulador(SimuladorFragment())
                        true
                    }
                    R.id.configuracion ->{
                        loadConfiguracion(ConfiguracionFragment())
                        true
                    }
                    R.id.cerrarSesion ->{
                        goToLoginActivity()
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
                drawer_layout.closeDrawer(GravityCompat.START)
                true

            }
    }
    private fun goToLoginActivity() {
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun loadNotas(frag1 : NotasFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag1)
        ft.commit()
    }

    fun loadHorario(frag2 : HorarioFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag2)
        ft.commit()
    }

    fun loadCalendario(frag3 : CalendarioFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag3)
        ft.commit()
    }

    fun loadSimulador(frag4 : SimuladorFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag4)
        ft.commit()
    }

    fun loadConfiguracion(frag5: ConfiguracionFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag5)
        ft.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
