package com.appg.mungnyang.oguogu.contentresolver

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appg.mungnyang.oguogu.contentresolver.databinding.ItemLayoutBinding
import java.text.SimpleDateFormat

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.Holder>(){
    val TAG = "로그"
    val musicList = mutableListOf<Music>()
    var mediaPlayer : MediaPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    inner class Holder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        var musicUri: Uri? = null

        init {
            if(mediaPlayer != null){
                mediaPlayer?.release()
                mediaPlayer = null
            }
            itemView.setOnClickListener {
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        fun setMusic(music:Music){
            musicUri = music.getMusicUri()
            if(binding == null){
                Log.d(TAG, "binding이 널입니다")
            }
            binding.imageAlbum.setImageURI(music.getAlbumUri())
            binding.textArtist.text = music.artist
            binding.textTitle.text = music.title
            val sdf = SimpleDateFormat("mm:ss")
            binding.textDuration.text = sdf.format(music.duration)
        }
    }
}
