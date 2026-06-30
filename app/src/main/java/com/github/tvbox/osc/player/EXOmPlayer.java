package com.github.tvbox.osc.player;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.SelectionOverride;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.DefaultTrackNameProvider;
import com.google.android.exoplayer2.trackselection.TrackNameProvider;
import com.google.android.exoplayer2.util.MimeTypes;

import xyz.doikki.videoplayer.exo.ExoMediaPlayer;

public class EXOmPlayer extends ExoMediaPlayer {
    private String audioId = "";
    private String subtitleId = "";
    private final TrackNameProvider trackNameProvider = new DefaultTrackNameProvider(mAppContext);

    public EXOmPlayer(Context context) {
        super(context);
    }

    private DefaultTrackSelector getDefaultTrackSelector() {
        return (DefaultTrackSelector) mTrackSelector;
    }

    public TrackInfo getTrackInfo() {
        TrackInfo data = new TrackInfo();
        MappedTrackInfo trackInfo = getDefaultTrackSelector().getCurrentMappedTrackInfo();
        if (trackInfo != null) {
            getExoSelectedTrack();
            for (int groupArrayIndex = 0; groupArrayIndex < trackInfo.getRendererCount(); groupArrayIndex++) {
                TrackGroupArray groupArray = trackInfo.getTrackGroups(groupArrayIndex);
                for (int groupIndex = 0; groupIndex < groupArray.length; groupIndex++) {
                    TrackGroup group = groupArray.get(groupIndex);
                    for (int formatIndex = 0; formatIndex < group.length; formatIndex++) {
                        Format format = group.getFormat(formatIndex);
                        if (MimeTypes.isAudio(format.sampleMimeType)) {
                            String trackName = (data.getAudio().size() + 1) + "：" + trackNameProvider.getTrackName(format) + "[" + format.codecs + "]";
                            TrackInfoBean t = new TrackInfoBean();
                            t.name = trackName;
                            t.language = "";
                            t.trackId = formatIndex;
                            t.selected = !TextUtils.isEmpty(audioId) && audioId.equals(format.id);
                            t.trackGroupId = groupIndex;
                            t.renderId = groupArrayIndex;
                            data.addAudio(t);
                        } else if (MimeTypes.isText(format.sampleMimeType)) {
                            String trackName = (data.getSubtitle().size() + 1) + "：" + trackNameProvider.getTrackName(format);
                            TrackInfoBean t = new TrackInfoBean();
                            t.name = trackName;
                            t.language = "";
                            t.trackId = formatIndex;
                            t.selected = !TextUtils.isEmpty(subtitleId) && subtitleId.equals(format.id);
                            t.trackGroupId = groupIndex;
                            t.renderId = groupArrayIndex;
                            data.addSubtitle(t);
                        }
                    }
                }
            }
        }
        return data;
    }

    private void getExoSelectedTrack() {
        audioId = "";
        subtitleId = "";
        if (mInternalPlayer != null && mInternalPlayer.getCurrentTracks() != null) {
            for (Tracks.Group group : mInternalPlayer.getCurrentTracks().getGroups()) {
                for (int trackIndex = 0; trackIndex < group.length; trackIndex++) {
                    Format format = group.getTrackFormat(trackIndex);
                    if (MimeTypes.isAudio(format.sampleMimeType)) {
                        audioId = format.id;
                    }
                    if (MimeTypes.isText(format.sampleMimeType)) {
                        subtitleId = format.id;
                    }
                }
            }
        }
    }

    public void selectExoTrack(@Nullable TrackInfoBean videoTrackBean) {
        MappedTrackInfo trackInfo = getDefaultTrackSelector().getCurrentMappedTrackInfo();
        if (trackInfo != null) {
            if (videoTrackBean == null) {
                for (int renderIndex = 0; renderIndex < trackInfo.getRendererCount(); renderIndex++) {
                    if (trackInfo.getRendererType(renderIndex) == C.TRACK_TYPE_TEXT) {
                        DefaultTrackSelector.Parameters.Builder parametersBuilder = getDefaultTrackSelector().getParameters().buildUpon();
                        parametersBuilder.setRendererDisabled(renderIndex, true);
                        getDefaultTrackSelector().setParameters(parametersBuilder);
                        break;
                    }
                }
            } else {
                TrackGroupArray trackGroupArray = trackInfo.getTrackGroups(videoTrackBean.renderId);
                SelectionOverride override = new SelectionOverride(videoTrackBean.trackGroupId, videoTrackBean.trackId);
                DefaultTrackSelector.Parameters.Builder parametersBuilder = getDefaultTrackSelector().getParameters().buildUpon();
                parametersBuilder.setRendererDisabled(videoTrackBean.renderId, false);
                parametersBuilder.setSelectionOverride(videoTrackBean.renderId, trackGroupArray, override);
                getDefaultTrackSelector().setParameters(parametersBuilder);
            }
        }

    }

    public void setOnTimedTextListener(Player.Listener listener) {
        mInternalPlayer.addListener(listener);
    }

}
