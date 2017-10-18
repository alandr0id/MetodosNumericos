package com.yeik.app.metodosnumericos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yeik.app.metodosnumericos.biseccion.BiseccionActivity
import com.yeik.app.metodosnumericos.euler.EulerActivity
import com.yeik.app.metodosnumericos.falsaposicion.FalsaActivity
import com.yeik.app.metodosnumericos.muller.MullerActivity
import com.yeik.app.metodosnumericos.newton.NewtonActivity
import com.yeik.app.metodosnumericos.puntofijo.PuntoFijoActivity
import com.yeik.app.metodosnumericos.richmond.RichmondActivity
import com.yeik.app.metodosnumericos.secante.SecanteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        punto_fijo_button.setOnClickListener {
            startActivity(Intent(this, PuntoFijoActivity::class.java))
        }

        biseccion_button.setOnClickListener {
            startActivity(Intent(this, BiseccionActivity::class.java))
        }

        falsa_posicion_button.setOnClickListener {
            startActivity(Intent(this, FalsaActivity::class.java))
        }

        newton_button.setOnClickListener {
            startActivity(Intent(this, NewtonActivity::class.java))
        }

        euler_button.setOnClickListener {
            startActivity(Intent(this, EulerActivity::class.java))
        }

        richmond_button.setOnClickListener {
            startActivity(Intent(this, RichmondActivity::class.java))
        }

        secante_button.setOnClickListener {
            startActivity(Intent(this, SecanteActivity::class.java))
        }

        muller_button.setOnClickListener {
            startActivity(Intent(this, MullerActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//
//        }
//    }
}
