// Java program to play an Audio 
// file using Clip Object 
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 
import java.util.*;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{ 

	public static PriorityQueue<String> pq=new PriorityQueue<>();
	// to store current position 
	Long currentFrame; 
	Clip clip; 
	
	// current status of clip 
	String status; 
	
	AudioInputStream audioInputStream; 
	static String filePath; 

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer() 
		throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException 
	{ 
		// create AudioInputStream object 
		audioInputStream = 
				AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	} 
    public static Stack<String> history=new Stack<>();
    public static HashMap<String,String> map=new HashMap<>();
    public static String getChoice(){
        Scanner scn=new Scanner(System.in);
		Scanner sc=new Scanner(System.in);
		
        map.put("Aate Jaate Khoobsurat Aawara","Aate Jaate Khoobsurat Aawara.wav");
		map.put("Attention","Attention.wav");
		map.put("Baby","Baby.wav");
		map.put("Chanda hai tu mera","Chanda Hai Tu Mera.wav");
		map.put("Dyanamite","Dyanamite.wav");
		map.put("Girls Like You","Girls Like You.wav");
		map.put("Jab Koi Baat Bigad Jaye","Jab Koi Baat Bigad Jaye.wav");
		map.put("Memories","Memories.wav");
		map.put("Mere Mehboob","Mere Mehboob Qayamat Hogi.wav");
		map.put("Monster","Monster.wav");
		map.put("Ruk Jana Nahin","Ruk Jana Nahin.wav");
		map.put("Speechless","Speechless.wav");
		map.put("Tu Kitni Achhi Hai","Tu Kitni Achhi Hai.wav");
		map.put("Tujhse Naraz Nahin Zindagi","Tujhse Naraz Nahin Zindagi.wav");
		map.put("Uptown Funk","Uptown Funk.wav");
		map.put("We Don't Talk Anymore","We Don't Talk Anymore.wav");
		map.put("What Makes You Beautiful","What Makes You Beautiful.wav");
		map.put("Yeh Duniya Yeh Mehfil","Yeh Duniya Yeh Mehfil.wav");
		map.put("Zindagi Ek Safar Hai Suhana","Zindagi Ek Safar Hai Suhana.wav");

        if(pq.size()!=0){
			System.out.println("Wanna choose from your playlist?");
			String ans=scn.next();
			if(ans.equalsIgnoreCase("no"))
			{
				for(String keys:map.keySet())
					System.out.println("* "+keys);
			}
			else
			{	
				PriorityQueue<String> pq1=new PriorityQueue<>();
				pq1.addAll(pq);
				while(pq1.size()!=0){
					System.out.println("#"+pq1.remove());
				}
			}
		}
		else{
			for(String keys:map.keySet())
					System.out.println("* "+keys);
		}
        System.out.println("Choose your song");
        String choice=sc.nextLine();
        return map.get(choice);
    }
    public static void viewhistory(){
		Stack hist1 = ( Stack ) history.clone();
		while(hist1.size()!=0){
        System.out.println(hist1.pop());
		}
	}
     
	public static void main(String[] args) 
	{ 
		try
		{ 
			boolean flag=true;
			do{
				filePath = getChoice(); 
				history.push(filePath);
				SimpleAudioPlayer audioPlayer = 
								new SimpleAudioPlayer(); 
				
				audioPlayer.play(); 
				System.out.println("Love this song? Create your playlist.....!!!");
				System.out.println("To add this song to your favorites, go to option no. 7");
				
				Scanner sc = new Scanner(System.in); 
				
					while (true) 
					{ 
						System.out.println("1. pause"); 
						System.out.println("2. resume"); 
						System.out.println("3. restart"); 
						System.out.println("4. stop"); 
						// System.out.println("5. stop"); 
						System.out.println("5. Jump to specific time");
						System.out.println("6. View history"); 
						System.out.println("7. Add to favorites"); 
						// System.out.println("8. Go to songs"); 
						
						int c = sc.nextInt(); 
						audioPlayer.gotoChoice(c); 
						if (c == 4) 
						break; 
						// sc.close();
					} 
					Scanner scn=new Scanner(System.in);
					System.out.println("Do you wanna continue??");
					String ans=scn.nextLine();
					// sc.close();
					if(ans.equalsIgnoreCase("yes")){
						flag=true;
					}
					else{
						flag=false;
					}
			}while(flag);
			 
		} 
		
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		
		} 
	} 
	
	// Work as the user enters his choice 
	
	private void gotoChoice(int c) 
			throws IOException, LineUnavailableException, UnsupportedAudioFileException 
	{ 
		switch (c) 
		{ 
			case 1: 
				pause(); 
				break; 
			case 2: 
				resumeAudio(); 
				break; 
			case 3: 
				restart(); 
				break; 
			case 4: 
				stop(); 
				break; 
			case 5: 
				System.out.println("Enter time (" + 0 + 
				", " + clip.getMicrosecondLength() + ")"); 
				Scanner sc = new Scanner(System.in); 
				long c1 = sc.nextLong(); 
				jump(c1); 
				break; 
            case 6:
                viewhistory();
                break;
			case 7:
				pause();
				AddTofavorites();
				resumeAudio();
				break;
		} 
	
	} 
	
	// Method to play the audio 
	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		
		status = "play"; 
	} 
	
	// Method to pause the audio 
	public void pause() 
	{ 
		if (status.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		this.currentFrame = 
		this.clip.getMicrosecondPosition(); 
		clip.stop(); 
		status = "paused"; 
	} 
	
	// Method to resume the audio 
	public void resumeAudio() throws UnsupportedAudioFileException, 
								IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) 
		{ 
			System.out.println("Audio is already "+ 
			"being played"); 
			return; 
		} 
		clip.close(); 
		resetAudioStream(); 
		clip.setMicrosecondPosition(currentFrame); 
		this.play(); 
	} 
	
	// Method to restart the audio 
	public void restart() throws IOException, LineUnavailableException, 
											UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
	// Method to stop the audio 
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException 
	{ 
		currentFrame = 0L; 
		clip.stop(); 
		clip.close(); 
	} 
	
	// Method to jump over a specific part 
	public void jump(long c) throws UnsupportedAudioFileException, IOException, 
														LineUnavailableException 
	{ 
		if (c > 0 && c < clip.getMicrosecondLength()) 
		{ 
			clip.stop(); 
			clip.close(); 
			resetAudioStream(); 
			currentFrame = c; 
			clip.setMicrosecondPosition(c); 
			this.play(); 
		} 
	} 
	
	// Method to reset audio stream 
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioInputStream = AudioSystem.getAudioInputStream( 
		new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

	// to create playlist
	public void AddTofavorites()
	{
		pq.add(filePath);
		System.out.println("Song added successfully..!");
	}
} 
