package com.arirus.beatbox.trunk;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by whd on 2016/9/28.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;


    public static BeatBoxFragment NewInstance()
    {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //不重载onCreate应该也可以,Beatbox赋值在这一函数内,不过感觉这样不好
        //mBeatBox = new BeatBox(getActivity());
        View view = inflater.inflate(R.layout.fragment_beat_box,container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return view;
    }
    //视图承载
    private class SoundHolder extends RecyclerView.ViewHolder
    {
        private Button mButton;
        private Sound mSound;

        //创建viewHolder 并将相应的 view获取出来
        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });
        }

        public void bindSound(Sound sound)
        {
            mSound = sound;
            mButton.setText(mSound.getName());
        }
    }
    //RecycleView适配器
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>
    {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds)
        {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        //ViewHolder 与 Adapter 链接的地方 在此将数据传输绑定
        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        //获取具体个数的方法
        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
