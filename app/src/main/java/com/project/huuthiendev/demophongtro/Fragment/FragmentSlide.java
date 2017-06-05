package com.project.huuthiendev.demophongtro.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.huuthiendev.demophongtro.R;
import com.squareup.picasso.Picasso;

/**
 * Created by HUUTHIENDEV on 10/16/2016.
 */

public class FragmentSlide extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_slide, container, false);
        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");

        ImageView imgSlide = (ImageView) view.findViewById(R.id.imgSlide);
        Picasso.with(getContext()).load(linkhinh).into(imgSlide);

        return view;
    }
}
