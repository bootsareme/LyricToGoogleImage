# LyricToGoogleImage
Takes in lyrics from any song and finds the corresponding Google image for it. 

DEMO - https://www.youtube.com/watch?v=hJvK3WVyIfw (explicit lyrics)

<h2>Installation</h2>

Go to https://github.com/bootsareme/LyricToGoogleImage/releases to download the latest version of LyricToGoogleImage. It is an executable JAR file. This means that you will need the latest Java runtime environment installed on your computer for it to work. Once the JAR file has been downloaded, double click to run the JAR.

<h2>Usage</h2>
To use LyricToGoogleImage, you will need to obtain an API key from Google to use their search engine. You will need to go to https://developers.google.com/custom-search/v1/introduction to obtain the key. Do not worry about anything else listed on the article, you only need to click the blue button that says "Get a Key". Follow the prompt to generate a key.
After you have obtained the API key, run the JAR file. A window should display. There will be a field for you to enter the API key and 2 buttons for you to select a  file containing lyrics and an image download directory. 

It is preferred to style your lyric text file like a poem (see below for examples):

WRONG:
```
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
```
CORRECT:
```
Lorem ipsum dolor sit amet, 
consectetur adipiscing elit,
sed do eiusmod tempor incididunt ut 
labore et dolore magna aliqua. 
Ut enim ad minim veniam, 
quis nostrud exercitation ullamco laboris 
nisi ut aliquip ex ea commodo consequat. 
Duis aute irure dolor in reprehenderit in 
voluptate velit esse cillum dolore eu 
fugiat nulla pariatur. Excepteur sint 
occaecat cupidatat non proident, sunt in 
culpa qui officia deserunt 
mollit anim id est laborum.
```

Make sure to delete the file contents of your folder if you decide to reuse it for another song. LyricToGoogleImage will error if it detects conflicting filenames.
<h2>Features</h2>
IMPORTANT: The Google Search API limits to 100 queries per day for free users. This means that for free users, only 100 images can be generated per day. If the program abruptly stops, this quota limit may have been reached. The lightweight edition attempts to alleviate this quota limit by generating one image for each UNIQUE word. With this in mind, repetitive words count as one, thereby allowing more images to be generated. The images will be out of order, so use the lightweight edition at your discretion.
<h3>Standard Edition</h3>
The Standard Edition downloads an image for each word in 'lyrics.txt' file. In the download directory, images label from '000.png' to 'n.png' for n words in 'lyrics.txt'. As discussed above, repetitive lyrics will not be accounted for. Only use this edition if you increase your quota.
<h3>Lightweight Edition</h3>
The lightweight edition downloads an image for each UNIQUE word in 'lyrics.txt'. In the download directory, images are labeled by their corresponding lyric. This will result in the images being out of order, as some repetitive lyrics only appear once in this edition.

<h2>Reporting Bugs</h2>

If you happen to experience a bug, please read above CAREFULLY before filing an issue. Quota limits and lyric formatting are the most common types of false positives. But, if none of the above applies, file an issue in this repository. Describe in detail what the issue is (complete with screenshots, logs, error messages, etc.), and steps to reproduce it. 