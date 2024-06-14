package com.android.shedule

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.shedule.ui.theme.SheduleTheme



class MainActivity : ComponentActivity() {
    companion object {
        const val TOTAL_NUMBER = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = resources.getColor(R.color.sheduleBackgroundColor)
        window.statusBarColor = resources.getColor(R.color.sheduleBackgroundColor)
        splashScreen()
        prgBarAnim()

    }


    private fun splashScreen(){
        Handler().postDelayed(Runnable {
            run(){
                val scheduleActivity = Intent(this, ScheduleActivity::class.java)
                startActivity(scheduleActivity)
                finish()
            }
        }, 1500)
    }

//    fun another(view: View) = startActivity(anotherIntent)

    private fun prgBarAnim(){
        val prgBar = findViewById<ProgressBar>(R.id.prgBar)
        prgBar.max = 500
        ObjectAnimator.ofInt(prgBar,"Progress", 500)
            .setDuration(1500)
            .start()
    }


}
//Шмурдяка не нужная (для превью штуки)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SheduleTheme {
        Greeting("Android")
    }
}