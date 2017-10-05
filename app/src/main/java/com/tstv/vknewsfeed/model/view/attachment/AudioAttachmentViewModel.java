package com.tstv.vknewsfeed.model.view.attachment;

import android.view.View;

import com.tstv.vknewsfeed.common.utils.Utils;
import com.tstv.vknewsfeed.model.attachment.Audio;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.ui.holder.attachment.AudioAttachmentHolder;

/**
 * Created by tstv on 19.09.2017.
 */

public class AudioAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mArtist;

    private String mDuration;


    public AudioAttachmentViewModel(Audio audio) {
        if (audio.getTitle().equals("")) {
            mTitle = "Title";
        } else {
            mTitle = audio.getTitle();
        }

        if (audio.getArtist().equals("")) {
            mArtist = "Various Artist";
        } else {
            mArtist = audio.getArtist();
        }

        mDuration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    @Override
    public AudioAttachmentHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getDuration() {
        return mDuration;
    }
}