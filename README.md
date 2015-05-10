Exqusite Lyrics is a Java application that uses lyric snippets from the MusixMatch API to create an exquisite corpse built to run on Google App Engine.

#What is Exqusite Corpse?

From Wikipedia:
>Exquisite corpse, also known as exquisite cadaver (from the original French term cadavre exquis) or rotating corpse, is a method by which a collection of words or images is collectively assembled. Each collaborator adds to a composition in sequence, either by following a rule (e.g. "The adjective noun adverb verb the adjective noun", as in "The green duck sweetly sang the dreadful dirge") or by being allowed to see only the end of what the previous person contributed.


#Running The Program
Before you can run this program you will need to request a MusixMatch API key, which can be done here: https://developer.musixmatch.com
You will also need to register your version of the application with Twitter here:http://twitter.com/oauth_clients/new
Finally, you will need a Google App Engine application ID, which can be acquired here: https://cloud.google.com/appengine/

The application looks for the MusixMatch API key in a text file called mmapikey.txt. The Twitter Consumer Key and Consumer Secret need to be located in a file called twitterkeys.txt. Both files should be placed in the root of the webapp directory.

In order for the app to run on Google App Engine you will need to add a file called app-engine.xml to the WEB-INF directory. Learn more about running a Java app on App Engine here: https://cloud.google.com/appengine/docs/java/gettingstarted/ui_and_code 

#Dependencies
jMusixMatch (https://github.com/sachin-handiekar/jMusixMatch)
Twitter4J (https://github.com/yusuke/twitter4j)
