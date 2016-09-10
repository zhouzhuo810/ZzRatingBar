package me.zhouzhuo.zzratingbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import me.zhouzhuo.zzratingbar.ZzRatingBar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private ZzRatingBar ratingBar;

    private TextView tvResult;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnSizeOne;
    private Button btnSizeTwo;
    private Button btnSizeThree;
    private Button btnCountFour;
    private Button btnCountFive;
    private Button btnStyleOne;
    private Button btnStyleTwo;
    private SwitchCompat sw;
    private SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initView() {
        ratingBar = (ZzRatingBar) findViewById(R.id.zzratingbar);

        tvResult = (TextView) findViewById(R.id.tv_result);
        sb = (SeekBar) findViewById(R.id.seekbar);
        sw = (SwitchCompat) findViewById(R.id.sw);
        sw.setChecked(ratingBar.isClickEnable());
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button) findViewById(R.id.btn_three);
        btnFour = (Button) findViewById(R.id.btn_four);
        btnSizeOne = (Button) findViewById(R.id.btn_size_one);
        btnSizeTwo = (Button) findViewById(R.id.btn_size_two);
        btnSizeThree = (Button) findViewById(R.id.btn_size_three);
        btnCountFour = (Button) findViewById(R.id.btn_count_four);
        btnCountFive = (Button) findViewById(R.id.btn_count_five);
        btnStyleOne = (Button) findViewById(R.id.btn_style_one);
        btnStyleTwo = (Button) findViewById(R.id.btn_style_two);

    }

    private void initEvent() {
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnSizeOne.setOnClickListener(this);
        btnSizeTwo.setOnClickListener(this);
        btnSizeThree.setOnClickListener(this);
        btnCountFour.setOnClickListener(this);
        btnCountFive.setOnClickListener(this);
        btnStyleOne.setOnClickListener(this);
        btnStyleTwo.setOnClickListener(this);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //是否支持手动修改
                ratingBar.setClickEnable(isChecked);
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //设置水平间距
                ratingBar.setSpacingInPixel(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //set rating changed listener
        ratingBar.setOnRatingChangedListener(new ZzRatingBar.OnRatingChangedListener() {
            @Override
            public void onRatingChanged(int current, int count) {
                tvResult.setText("rating:" + current + ",total:" + count);
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                ratingBar.setRating(1);
                break;
            case R.id.btn_two:
                ratingBar.setRating(2);
                break;
            case R.id.btn_three:
                ratingBar.setRating(3);
                break;
            case R.id.btn_four:
                //修改星级
                ratingBar.setRating(4);
                break;
            case R.id.btn_size_one:
                ratingBar.setStarSizeInPixel(30);
                break;
            case R.id.btn_size_two:
                ratingBar.setStarSizeInPixel(50);
                break;
            case R.id.btn_size_three:
                //修改尺寸
                ratingBar.setStarSizeInPixel(70);
                break;
            case R.id.btn_count_four:
                //修改总数
                ratingBar.setStarCount(4);
                break;
            case R.id.btn_count_five:
                ratingBar.setStarCount(5);
                break;
            case R.id.btn_style_one:
                //修改样式
                ratingBar.setNormalStarDrawable(R.drawable.fgm_home_iv_gray_point);
                ratingBar.setCheckedStarDrawable(R.drawable.fgm_home_iv_green_point);
                break;
            case R.id.btn_style_two:
                ratingBar.setNormalStarDrawable(R.drawable.default_star_normal);
                ratingBar.setCheckedStarDrawable(R.drawable.default_star_checked);
                break;
        }
    }
}
