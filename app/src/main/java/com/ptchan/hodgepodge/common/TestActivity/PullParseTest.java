package com.ptchan.hodgepodge.common.TestActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ptchan.hodgepodge.R;
import com.ptchan.hodgepodge.user.Weather;

import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentao on 16-8-27.
 */
public class PullParseTest extends Activity{

    private Button mButton;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltest);
        mButton = (Button)this.findViewById(R.id.btn_pull);
        mEditText = (EditText)this.findViewById(R.id.et_pull);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder("");
                    InputStream is = getClassLoader().getResourceAsStream("<weather>" +
                            "    <city>" +
                            "        <name>上海</name>" +
                            "        <temp>8°</temp>" +
                            "        <pm>80</pm>" +
                            "    </city>" +
                            "    <city>" +
                            "        <name>北京</name>" +
                            "        <temp>3°</temp>" +
                            "        <pm>300</pm>" +
                            "    </city>" +
                            "    <city>" +
                            "        <name>西安</name>" +
                            "        <temp>12°</temp>" +
                            "        <pm>60</pm>" +
                            "    </city>" +
                            "</weather>");
                    List<Weather> list = readXML(is);

            }
        });
    }

    private List readXML(InputStream is) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is,"utf-8");  // 设置数据源编码
            int eventType = parser.getEventType(); // 获取事件类型
            Weather curWeather = null;
            List<Weather> weather = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        weather = new ArrayList<>(); //实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，
                        // 或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("city")){
                            curWeather = new Weather();
                            curWeather.setId(Integer.valueOf(parser.getAttributeValue(null,"id")));
                        }else if (curWeather != null){
                            if (name.equalsIgnoreCase("name")){
                                curWeather.setName(parser.nextText());
                            }else if (name.equalsIgnoreCase("temp")){
                                curWeather.setTemp(parser.nextText());
                            }else if (name.equalsIgnoreCase("pm")){
                                curWeather.setPm(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:  //结束元素事件
                        //读完一个City，可以将其添加到集合类中
                        if (parser.getName().equalsIgnoreCase("city") && curWeather != null){
                            weather.add(curWeather);
                            curWeather = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
