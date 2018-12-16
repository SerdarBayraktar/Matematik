package com.serdarbayraktarmath.matematik;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    ArrayList<classssss> stateList;

    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();

    }

    private void displayListView()
    {

        //Array list of countries
        stateList = new ArrayList<classssss>();

        classssss _states = new classssss("AP","Andhra Pradesh",false);
        stateList.add(_states);
        _states = new classssss("DL","Delhi",true);
        stateList.add(_states);
        _states = new classssss("GA","Goa",false);
        stateList.add(_states);
        _states = new classssss("JK","Jammu & Kashmir",true);
        stateList.add(_states);
        _states = new classssss("KA","Karnataka",true);
        stateList.add(_states);
        _states = new classssss("KL","Kerala",false);
        stateList.add(_states);
        _states = new classssss("RJ","Rajasthan",false);
        stateList.add(_states);
        stateList.add(new classssss("WB","West Bengal",false));
        ParseQuery<ParseObject> query = ParseQuery.getQuery("main1");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else {
                    if (objects.size()>0){
                        for (final ParseObject object : objects){
                            stateList.add(new classssss(object.getString("kitap"),object.getString("topic"),false));
                        }
                    }
                }
            }
        });

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,R.layout.customview, stateList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener()
        {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // When clicked, show a toast with the TextView text
                classssss state = (classssss) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Clicked on Row: " + state.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<classssss>
    {

        private ArrayList<classssss> stateList;

        public MyCustomAdapter(Context context, int textViewResourceId,

                               ArrayList<classssss> stateList)
        {
            super(context, textViewResourceId, stateList);
            this.stateList = new ArrayList<classssss>();
            this.stateList.addAll(stateList);
        }

        private class ViewHolder
        {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null)
            {

                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.customview, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);

                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        CheckBox cb = (CheckBox) v;
                        classssss _state = (classssss) cb.getTag();

                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();

                        _state.setSelected(cb.isChecked());
                    }
                });

            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            classssss state = stateList.get(position);

            holder.code.setText(" (" + state.getCode() + ")");
            holder.name.setText(state.getName());
            holder.name.setChecked(state.isSelected());

            holder.name.setTag(state);

            return convertView;
        }

    }

    private void checkButtonClick()
    {

        Button myButton = (Button) findViewById(R.id.findSelected);

        myButton.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<classssss> stateList = dataAdapter.stateList;

                for(int i=0;i<stateList.size();i++)
                {
                    classssss state = stateList.get(i);

                    if(state.isSelected())
                    {
                        responseText.append("\n" + state.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();
            }
        });
    }
    public  void download(){


    }






/*


    public void abc (View view){

        Boolean b =Checkbox.isChecked();
        System.out.println(b);
        Checkbox.setText("asd");
        Checkbox= (CheckBox) findViewById(R.id.checkBox) ;
        Boolean a =Checkbox.isChecked();
        System.out.println(a);


        ParseObject abc = new ParseObject("test");

        abc.put("student","ali");
        abc.put("book","palme");
        abc.put("topic","carpma");
        abc.put("test","1-10");
        abc.put("status","1");
        abc.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
 */





    }
