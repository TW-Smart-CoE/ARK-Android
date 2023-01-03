package com.thoughtworks.ark.sample.di

import android.content.Context
import com.thoughtworks.ark.media.sound.alert.AlertPlayer
import com.thoughtworks.ark.media.sound.alert.AlertPlayerImpl
import com.thoughtworks.ark.media.sound.media.MediaPlayer
import com.thoughtworks.ark.media.sound.media.MediaPlayerImpl
import com.thoughtworks.ark.media.sound.tts.TTSSpeaker
import com.thoughtworks.ark.media.sound.tts.TTSSpeakerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SoundModule {

    @Provides
    fun providerAlertPlayer(@ApplicationContext context: Context): AlertPlayer = AlertPlayerImpl(context)

    @Provides
    fun providerMediaPlayer(@ApplicationContext context: Context): MediaPlayer = MediaPlayerImpl(context)

    @Provides
    fun providerTTSSpeaker(@ApplicationContext context: Context): TTSSpeaker = TTSSpeakerImpl(context)
}