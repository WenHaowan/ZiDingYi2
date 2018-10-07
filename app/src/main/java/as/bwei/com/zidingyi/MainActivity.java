package as.bwei.com.zidingyi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerView=(BannerView)findViewById(R.id.banner);
        List<String> stringList=new ArrayList<>();
        stringList.add("https://img.huxiucdn.com/article/cover/201809/28/075950038970.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg");
        stringList.add("https://img.huxiucdn.com/article/cover/201809/28/092402415399.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg");
        stringList.add("https://img.huxiucdn.com/article/cover/201809/28/080420659171.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg");
        bannerView.setImageList(stringList);
        bannerView.setBannerTime(2000);
        bannerView.startAndCloseBanner(true);
    }
}
