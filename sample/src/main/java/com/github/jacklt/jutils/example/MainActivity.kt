package com.github.jacklt.jutils.example

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.github.jacklt.jutils.R

class MainActivity : AppCompatActivity() {
    private val rootView by lazy { findViewById<LinearLayout>(R.id.rootView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addText("myBoolean: ${FRC.myBoolean}")
        addText("myInt: ${FRC.myInt}")
        addText("myLong: ${FRC.myLong}")
        addText("myDouble: ${FRC.myDouble}")
        addText("myString: ${FRC.myString}")

        if (FRC.secretFeature1_enabled) {
            addText("Feature 1 enabled! Try it now!")
        }

        if (FRC.secretFeature2_enabled) {
            addText("Feature 2 enabled! Try it now!")
        }

        // snackbar("Start fetch")
        // FRC.fetch { snackbar("Fetch complete: ${it.isComplete}") }
    }

    // Utils
    private fun log(msg: String) = Log.d("SampleEasyFRC", msg)
    private fun addText(msg: String) {
        log("Add TextView: $msg")
        rootView.addView(TextView(this).apply { text = msg })
    }
    private fun snackbar(msg: String) {
        log("Snackbar: $msg")
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show()
    }
}
