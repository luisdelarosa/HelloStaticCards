package com.luisdelarosa.hellostaticcards;

import java.util.ArrayList;

import com.google.android.glass.app.Card;
import com.google.android.glass.timeline.TimelineManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.RecognizerIntent;
import android.util.Log;

public class InitialService extends Service {

	private static final String LOG_TAG = "InitialService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    ArrayList<String> voiceResults = intent.getExtras()
	            .getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
	    
	    Card staticCard = createStaticCard(voiceResults);
	    long staticCardId = publishStaticCardToTimeline(staticCard);
	    Log.d(LOG_TAG, String.format("Published static card to the timeline with id:%d and text:%s", staticCardId, staticCard.getText()));
	    
	    return START_STICKY;
	}

	private long publishStaticCardToTimeline(Card staticCard) {
		TimelineManager timelineManager = TimelineManager.from(this);
		// Note that it seems like a sound is played when this card is inserted automatically.
		long staticCardId = timelineManager.insert(staticCard);
		return staticCardId;
	}

	private Card createStaticCard(ArrayList<String> voiceResults) {
		// For now, just use the first result
		// TODO concatenate all the voiceResults into one string
		String text = voiceResults.get(0);
		Card card = new Card(this);
		card.setText(text);
		return card;
	}
}
