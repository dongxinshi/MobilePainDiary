package com.example.mobilepaindiary.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.databinding.ActivityLoginBinding;
import com.example.mobilepaindiary.databinding.AddFragmentBinding;
import com.example.mobilepaindiary.databinding.LinechartBinding;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.util.ArrayList;
import java.util.List;

public class Linechart extends AppCompatActivity {

    private LinechartBinding binding;
    public Linechart(){
        //
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LinechartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);





        binding.saveT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Entry> valsComp1 = new ArrayList<Entry>();
                List<Entry> valsComp2 = new ArrayList<Entry>();

                Entry c1e1 = new Entry(3, 1); // 0 == quarter 1
                valsComp1.add(c1e1);
                Entry c1e2 = new Entry(4, 2); // 1 == quarter 2 ...
                valsComp1.add(c1e2);
                Entry c1e3 = new Entry(5, 3); // 0 == quarter 1
                valsComp1.add(c1e3);
                Entry c1e4 = new Entry(6, 4); // 1 == quarter 2 ...
                valsComp1.add(c1e4);
                Entry c1e5 = new Entry(7, 5); // 0 == quarter 1
                valsComp1.add(c1e5);
                Entry c1e6 = new Entry(8, 6); // 1 == quarter 2 ...
                valsComp1.add(c1e6);
                Entry c1e7 = new Entry(9, 7); // 0 == quarter 1
                valsComp1.add(c1e7);
                Entry c1e8 = new Entry(10, 8); // 1 == quarter 2 ...
                valsComp1.add(c1e8);
                // and so on ...

                Entry c2e1 = new Entry(3, 21); // 0 == quarter 1
                valsComp2.add(c2e1);
                Entry c2e2 = new Entry(4, 22); // 1 == quarter 2 ...
                valsComp2.add(c2e2);
                Entry c2e3 = new Entry(5, 23); // 0 == quarter 1
                valsComp2.add(c2e3);
                Entry c2e4 = new Entry(6, 23); // 1 == quarter 2 ...
                valsComp2.add(c2e4);
                Entry c2e5 = new Entry(7, 25); // 0 == quarter 1
                valsComp2.add(c2e5);
                Entry c2e6 = new Entry(8, 27); // 1 == quarter 2 ...
                valsComp2.add(c2e6);
                Entry c2e7 = new Entry(9, 27); // 0 == quarter 1
                valsComp2.add(c2e7);
                Entry c2e8 = new Entry(10, 28); // 1 == quarter 2 ...
                valsComp2.add(c2e8);





                LineDataSet linearDatas1 = new LineDataSet(valsComp1,"Pain level");
                LineDataSet linearDatas2 = new LineDataSet(valsComp2,"temperature");
                linearDatas1.setAxisDependency(YAxis.AxisDependency.LEFT);
                linearDatas2.setAxisDependency(YAxis.AxisDependency.RIGHT);

                ArrayList<Integer> colors = new ArrayList<Integer>();
                colors.add(getResources().getColor(R.color.purple_200));
                //colors.add(getResources().getColor(R.color.green));
                linearDatas2.setColors(colors);
                // Set you LinearData

                List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                dataSets.add(linearDatas1);
                dataSets.add(linearDatas2);



                LineData data = new LineData(dataSets);



                binding.chart1.setData(data);
                binding.chart1.invalidate();

            }
        });

        binding.editT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textView11.setText("R value:0.498971312");
                binding.textView12.setText("P value:0.592834754");


            }
        });













    }


}
