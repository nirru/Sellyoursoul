package com.sell.soul;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriteDetailFragment extends Fragment {

    EditText _desc,_author;
    TextView text1,text2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    int pos = 0;
    ImageView _image_View,_image_View1;
    WebView webView;

    AppCompatButton next_btn;

    private static final long DURATION = 2500;
    private Handler handler;

    public WriteDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WriteDetailFragment newInstance(String param1, String param2) {
        WriteDetailFragment fragment = new WriteDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_write_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View v) {
        text1 = (TextView) v.findViewById(R.id.desc_1);
        text2 = (TextView) v.findViewById(R.id.des_2);

        _desc = (EditText)v.findViewById(R.id.desc);
        _author = (EditText)v.findViewById(R.id.author_name);


        text1.setText("In the name of Satan,Lucifer,Belial & Leviathan and in the company of Astaroth, Beelzebub, Asmodeus, Abbadon & Azazel & by all the demons named and nameless who swam the Pits of Hell,I do happy to sell my soul to the Devil,Satan-Lucifer in the exchange for");

        _image_View = (ImageView)v.findViewById(R.id.image);
        _image_View1 =  (ImageView)v.findViewById(R.id.image1);
        webView = (WebView)v.findViewById(R.id.webview);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setBackgroundColor(Color.TRANSPARENT);


        handler = new Handler(Looper.getMainLooper());

        next_btn = (AppCompatButton)v.findViewById(R.id.next);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_desc.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Please write some story",Toast.LENGTH_SHORT).show();
                }
               else if(_author.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Please write author name",Toast.LENGTH_SHORT).show();
                }
                else{
                    webView.setVisibility(View.GONE);
                    text1.setVisibility(View.GONE);
                    text2.setVisibility(View.GONE);
                    _desc.setVisibility(View.GONE);
                    _author.setVisibility(View.GONE);
                    next_btn.setVisibility(View.GONE);
                   changeImage();
                }


            }
        });

        webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("second.html").toString(), "text/html", "utf-8", null);
    }


    private void changeImage() {
        webView.setVisibility(View.GONE);
        Animation animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        _image_View.setVisibility(View.VISIBLE);
        _image_View1.setVisibility(View.GONE);
        animFadeOut.reset();
        _image_View.clearAnimation();
        _image_View.startAnimation(animFadeOut);

        Animation animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        _image_View1.setVisibility(View.VISIBLE);
        animFadeIn.reset();
        _image_View1.clearAnimation();
        _image_View1.startAnimation(animFadeIn);

        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                _image_View.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Fragment fragment = LastFragment.newInstance("","");
                FragmentManager fragmentManager = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.addToBackStack(null);/**Enable this in fragment call not in activity*/
                fragmentTransaction.commit();

//                webView.setVisibility(View.VISIBLE);
//                next_btn.setEnabled(true);
//                text1.setVisibility(View.VISIBLE);
//                text2.setVisibility(View.VISIBLE);
//                _desc.setVisibility(View.VISIBLE);
//                _author.setVisibility(View.VISIBLE);
//                next_btn.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    private StringBuilder loadFromAsset(String html){
        BufferedReader in = null;
        StringBuilder buffer = new StringBuilder();

        String assetFile = html;

        try {

            in = new BufferedReader(new InputStreamReader(getActivity().getAssets().open( assetFile ),"utf-8"));
            String line;

    /* line by line read in the file */
            while ((line = in.readLine()) != null) buffer.append(line);

        } catch (IOException e) {
        } finally {
            try { in.close(); } catch (Exception e) {}
        }

        return buffer;
    }


}
