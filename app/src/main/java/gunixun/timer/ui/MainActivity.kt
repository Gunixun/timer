package gunixun.timer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import gunixun.timer.R
import gunixun.timer.databinding.ActivityMainBinding
import gunixun.timer.ui.timer.TimerFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setHomePage()
        }
    }

    private fun navigationTo(fragment: Fragment, withTransaction: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (withTransaction) {
            transaction.addToBackStack("Transaction")
        }

        transaction.commit()
    }

    private fun setHomePage() {
        navigationTo(TimerFragment.newInstance())
    }
}

