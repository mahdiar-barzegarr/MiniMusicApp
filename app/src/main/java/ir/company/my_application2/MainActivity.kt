package ir.company.my_application2

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.company.my_application2.databinding.ActivityMainBinding
import ir.company.my_application2.databinding.BottomSheetDialogBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.fabStart.setOnClickListener { startMusic()}
        binding.fabPause.setOnClickListener { pauseMusic()}
        binding.fabStop.setOnClickListener { stopMusic() }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


    }

    private fun startMusic() {
        if (mediaPlayer == null){
//            mediaPlayer = MediaPlayer.create(this,R.raw.music)
            val uri = "https://xx.venusmusic.ir/Archive/A/Ali%20Sorena/Ali%20Sorena%20-%20Mojassameh/Ali%20Sorena%20-%20Mojassameh%20128/05%20Ali%20Sorena%20-%20Bandbaze%20Mast%20128.mp3"

            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            mediaPlayer?.setDataSource(uri)
            mediaPlayer?.prepare()

            mediaPlayer?.setOnCompletionListener{stopMusic()}

            initSeekBar()
        }


        mediaPlayer?.start()
    }

    private fun pauseMusic() {

            mediaPlayer?.pause()


    }

    private fun stopMusic() {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
    }

    override fun onStop() {
        super.onStop()

        stopMusic()
    }

    private fun initSeekBar(){
        binding.seekBar.max = mediaPlayer?.duration?:100

        val handler = Handler(mainLooper)

        handler.postDelayed(object : Runnable{
            override fun run() {
                try {

                    binding.seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this,1000)
                }catch (e:Exception){
                    binding.seekBar.progress = 0
                }

            }

        },10000)
    }
    // =======================================important function in my app===================================================================================================================


    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    }












}