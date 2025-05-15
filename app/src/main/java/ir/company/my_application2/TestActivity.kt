package ir.company.my_application2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.company.my_application2.databinding.ActivityMainBinding
import ir.company.my_application2.databinding.ActivityTestBinding

class TestActitivty : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)









    }
}