# ZzRatingBar

A powerful RatingBar that can be customized easily.

**Gradle:**

```
compile 'me.zhouzhuo.zzratingbar:zz-rating-bar:1.0.0'
```

**Maven:**

```xml
<dependency>
  <groupId>me.zhouzhuo.zzratingbar</groupId>
  <artifactId>zz-rating-bar</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

<h3>What does it look like ?</h3>

![demo](http://img.blog.csdn.net/20160910150845391)



<h3>How to use it ?</h3>

①xml

```xml
        <me.zhouzhuo.zzratingbar.ZzRatingBar
            android:id="@+id/zzratingbar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:zrb_click_enable="false"
            app:zrb_horizontal_spacing="5dp"
            app:zrb_rating="0"
            app:zrb_star_count="4"
            app:zrb_star_dimension="20dp" />
```


②java

```
ratingBar = (ZzRatingBar) findViewById(R.id.zzratingbar);

//是否支持手动修改
ratingBar.setClickEnable(isChecked);

//设置水平间距
ratingBar.setSpacingInPixel(progress);

//修改星级
ratingBar.setRating(4);

//修改尺寸
ratingBar.setStarSizeInPixel(70);

//修改样式
ratingBar.setNormalStarDrawable(R.drawable.fgm_home_iv_gray_point);
ratingBar.setCheckedStarDrawable(R.drawable.fgm_home_iv_green_point);

//set rating changed listener
ratingBar.setOnRatingChangedListener(new ZzRatingBar.OnRatingChangedListener() {
    @Override
    public void onRatingChanged(int current, int count) {
        tvResult.setText("rating:" + current + ",total:" + count);
    }
});

```


<h3>属性说明：</h3>

| 属性| 说明| 类型|
| ------- |-----------| -----|
| zrb_rating | 当前星级 | 整数 |
| zrb_star_count | 总星级 | 整数 |
| zrb_click_enable | 是否支持手动修改 | boolean |
| zrb_star_dimension | 星星大小 | 尺寸(dp) |
| zrb_horizontal_spacing | 水平间距 | 尺寸(dp)|
| zrb_normal_star_res | 星星未选中时图片 | 图片资源id |
| zrb_checked_star_res | 星星选中时图片 | 图片资源id |
