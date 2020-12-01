# SimpleAudioPlayer plays an audio file in pure java, here pure means, we are not going to use any external library. Java inbuilt libraries support only AIFC, AIFF, AU, SND and WAVE formats.
There are 2 different interfaces which can be used for this purpose Clip and SourceDataLine. In this article, we will discuss playing audio file using Clip only and see the various methods of clip. We will cover following operations:

Start.
Pause.
Resume.
Restart.
Stop
Jump to a specific position of playback.
View history
Play Audio using Clip

Clip is a java interface available in javax.sound.sampled package and introduced in Java7.
Following steps are to be followed to play a clip object.
Create an object of AudioInputStream by using AudioSystem.getAudioInputStream(File file). AudioInputStream converts an audio file into stream.
Get a clip reference object from AudioSystem.
Stream an audio input stream from which audio data will be read into the clip by using open() method of Clip interface.
Set the required properties to the clip like frame position, loop, microsecond position.
Start the clip

In above program we have used AudioInputStream which is a class in Java to read audio file as a stream. Like every stream of java if it is to be used again it has to be reset.

To pause the playback we have to stop the player and store the current frame in an object. So that while resuming we can use it. When resuming we just have to play again the player from the last position we left.
clip.getMicrosecondPosition() method returns the current position of audio and clip.setMicrosecondPosition(long position) sets the current position of audio.
To stop the playback, you must have to close the clip otherwise it will remain open. I have also used clip.loop(Clip.LOOP_CONTINOUSLY) for testing. Because wav files are generally small so I have played mine in a loop.
