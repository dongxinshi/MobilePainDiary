package com.example.mobilepaindiary.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.databinding.ViewFragmentBinding;
import com.example.mobilepaindiary.ui.RegisterActivity;
import com.example.mobilepaindiary.ui.home.MainActivity;
import com.example.mobilepaindiary.ui.home.viewmodel.SharedViewModel;
import com.example.mobilepaindiary.ui.login.LoginActivity;
import com.example.mobilepaindiary.ui.room.User;
import com.example.mobilepaindiary.ui.room.UserViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://www.jianshu.com/p/802118e621d6
public class ViewFragment extends Fragment {
    private ViewFragmentBinding binding;
    private UserViewModel customerViewModel;


    public ViewFragment() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        customerViewModel   = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().
                getApplication()).create(UserViewModel.class);



        binding.piechart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.chart1.clear();
                /**
                List<PieEntry> entries = new ArrayList<>();

                entries.add(new PieEntry(18.5f, "Green"));
                entries.add(new PieEntry(26.7f, "Yellow"));
                entries.add(new PieEntry(24.0f, "Red"));
                entries.add(new PieEntry(30.8f, "Blue"));

                PieDataSet set = new PieDataSet(entries, "Election Results");
                PieData data = new PieData(set);
                binding.chart1.setData(data);
                binding.chart1.invalidate(); // refresh
                 */
                SharedPreferences sharedPref= getContext().
                        getSharedPreferences("Message", Context.MODE_PRIVATE);
                Float step = sharedPref.getFloat("step", 5000);
                Float goal = sharedPref.getFloat("goal", 10000);
                Float re = goal-step;
                if(re <0 ){
                    re = 0f;
                }



                List<PieEntry> strings = new ArrayList<>();
                strings.add(new PieEntry(step,"steps have taken"));
                strings.add(new PieEntry(re,"steps remaining"));

                PieDataSet dataSet = new PieDataSet(strings,"");

                ArrayList<Integer> colors = new ArrayList<Integer>();
                colors.add(getResources().getColor(R.color.purple_200));
                colors.add(getResources().getColor(R.color.green));
                dataSet.setColors(colors);

                Description description = new Description();
                description.setText("steps taken pie chart");
                binding.chart2.setDescription(description);

                PieData pieData = new PieData(dataSet);
                pieData.setDrawValues(true);

                pieData.setValueTextSize(14f);

                binding.chart2.setData(pieData);
                binding.chart2.invalidate();






            }
        });



        binding.piechart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 List<PieEntry> entries = new ArrayList<>();

                 entries.add(new PieEntry(18.5f, "Green"));
                 entries.add(new PieEntry(26.7f, "Yellow"));
                 entries.add(new PieEntry(24.0f, "Red"));
                 entries.add(new PieEntry(30.8f, "Blue"));

                 PieDataSet set = new PieDataSet(entries, "Election Results");
                 PieData data = new PieData(set);
                 binding.chart1.setData(data);
                 binding.chart1.invalidate(); // refresh
                 */

                List<PieEntry> strings = new ArrayList<>();


                binding.chart2.clear();
                LiveData<List<User>> allCustomers =
                        customerViewModel.getAllCustomers();

                ArrayList<Integer> colors = new ArrayList<Integer>();
                colors.add(getResources().getColor(R.color.purple_200));
                colors.add(getResources().getColor(R.color.green));
                colors.add(getResources().getColor(R.color.yellow));
                colors.add(getResources().getColor(R.color.red));
                colors.add(getResources().getColor(R.color.blue));
                colors.add(getResources().getColor(R.color.orange));
                colors.add(getResources().getColor(R.color.gray));
                colors.add(getResources().getColor(R.color.white));
                colors.add(getResources().getColor(R.color.olive));


                customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        ArrayList<String> a = new ArrayList<String>();
                        for (User temp : users) {
                            a.add(temp.getPosition());
                        }

                        Map map = frequencyOfListElements(a);

                        Set<String> keySet = map.keySet();
                        //遍历存放所有key的Set集合
                        Iterator<String> it =keySet.iterator();
                        while(it.hasNext()){                         //利用了Iterator迭代器**

                            String key = it.next();

                            Object value = map.get(key);
                            float val = Float.parseFloat(value.toString());
                            float re = val / a.size();
                            strings.add(new PieEntry(re,key));

                        }
                        PieDataSet dataSet = new PieDataSet(strings,"");
                        dataSet.setColors(colors);

                        Description description = new Description();
                        description.setText("pain location pie chart");
                        binding.chart2.setDescription(description);

                        PieData pieData = new PieData(dataSet);
                        pieData.setDrawValues(true);

                        //pieData.setValueFormatter(new PercentFormatter());
                        pieData.setValueFormatter(new PercentFormatter());


                        pieData.setValueTextSize(14f);

                        binding.chart1.setData(pieData);
                        binding.chart1.invalidate();
                    }
                });





















            }
        });
        binding.linechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft =
                        fragmentManager.beginTransaction();

                Intent intent=new Intent(getContext(), Linechart.class);


                startActivity(intent);



                //FragmentManager fm = getActivity().getFragmentManager();
               // FragmentTransaction ft=fragmentManager.beginTransaction();
               // ft.replace(R.id.container1,new Linechart());
                //ft.commit();




            }
        });




        return view;

    }

    public static Map<String,Integer> frequencyOfListElements(ArrayList<String> items ) {
        if (items == null || items.size() == 0) return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String temp : items) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
    }





}