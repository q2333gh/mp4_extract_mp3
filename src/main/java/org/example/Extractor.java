package org.example;


import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import java.io.File;
import java.util.ArrayList;

public class Extractor {
  public static void main(String[] args)  {
    System.out.println("Start extract ");
    ArrayList<String> folder = findAllFiles("T:\\video\\blance");
    for (String item : folder) {
      System.out.println("extracting :"+item);
      Mp4_extract_mp3(item);
    }
    System.out.println("Extract finish , file at: "+getCurrentPath());
  }
  public void doExtract(String path){
    System.out.println("Start extract ");
    ArrayList<String> folder = findAllFiles(path);
    for (String item : folder) {
      System.out.println("extracting :"+item);
      Mp4_extract_mp3(item);
    }
    System.out.println("Extract finish , file at: "+getCurrentPath());
  }

  static void Mp4_extract_mp3(String filePath )  {
    // Create a source file object
    File source = new File(filePath);

    String targetNameWithPath="./"+getName(filePath)+".mp3";
    // Create a target file object
    File target = new File(targetNameWithPath);

    // Create an encoder object
    Encoder encoder = new Encoder();

    // Create an encoding attributes object
    EncodingAttributes attrs = new EncodingAttributes();

    // Set the output format to MP3
    attrs.setFormat("mp3");

    // Set the audio attributes
    AudioAttributes audio = new AudioAttributes();

    // Set the codec to libmp3lame
    audio.setCodec("libmp3lame");

    // Set the sampling rate to 44100 Hz
    audio.setSamplingRate(44100);

    // Set the number of channels to 2 (stereo)
    audio.setChannels(2);

    // Set the bit rate to 128000 bps
    audio.setBitRate(128000);

    // Set the encoding attributes
    attrs.setAudioAttributes(audio);

    // Encode the source file to the target file
    try {
      encoder.encode(source, target, attrs);
    } catch (  EncoderException e) {
      throw new RuntimeException(e);
    }
  }
  static String getName(String path){
    // Split the string by \\ and store the result in an array
    String[] parts = path.split("\\\\");

    // Get the last element of the array
    String lastPart = parts[parts.length - 1];

    // Split the last element by . and store the result in another array
    String[] subParts = lastPart.split("\\.");

    // Get the first element of the second array
    String result = subParts[0];

    // Print the result
    return result;
  }
  static String getCurrentPath(){
    // Create a file object with the current directory
    File file = new File(".");

    // Print the current directory
    System.out.println(file.getPath());

    // Convert the file object to an absolute path
    File absoluteFile = file.getAbsoluteFile();

    // Print the absolute path
    return absoluteFile.getPath();
  }

  public static ArrayList<String> findAllFiles(String folderName) {
    // Create an arraylist to store the paths
    ArrayList<String> paths = new ArrayList<>();

    // Create a file object for the given folder name
    File folder = new File(folderName);

    // Check if the folder exists and is a directory
    if (folder.exists() && folder.isDirectory()) {
      // Get the list of files in the folder
      File[] files = folder.listFiles();
      // Loop through the files
      for (File file : files) {
        // Check if the file is not a directory
        if (!file.isDirectory()) {
          // Add the absolute path of the file to the arraylist
          paths.add(file.getAbsolutePath());
        }
      }
    }

    // Return the arraylist of paths
    return paths;
  }
}
