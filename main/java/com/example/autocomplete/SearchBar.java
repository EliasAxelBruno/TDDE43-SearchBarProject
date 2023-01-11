package com.example.autocomplete;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchBar extends androidx.appcompat.widget.AppCompatEditText implements AddLogic{
    EditText input;
    ArrayList<String> searchRes = new ArrayList<>();
    android.widget.ListPopupWindow listPopupWindow;
    Adapter adapter;
    public HashMap<Integer, String> data = new HashMap<Integer, String>();
    int id;



    public SearchBar(Context context) {
        super(context);

        input= this;
        setData();
        listPopupWindow = new ListPopupWindow(context);
        adapter = new Adapter(context, searchRes);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setAnchorView(input);


        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) { //Ny input
                if (s.length() != 0) {
                    searchRes.clear();
                    startSearch(input); //Hitta alla möjliga alternativ

                }else listPopupWindow.dismiss(); //Ta bort listPopupWindow om det searchbar är tom
            }
        });

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {//Om jag klickar på ett namn i listan så ska den gå vidare
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click(searchRes.get(position));
                input.setText(searchRes.get(position));
                setArrayAdapterListPopUp();

            }
        });
    }

    public SearchBar(Context context, AttributeSet aS){
        this(context);
    }

    public void onWindowFocusChanged(boolean hasFocus) { //Om rutan är klickad på eller ej
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            listPopupWindow.setHeight(800);
        }
    }

    public void startSearch(EditText toSearch) { //Här startar vi sökningen
        String input = toSearch.getText().toString();


        for (Map.Entry<Integer, String> entry : data.entrySet()) { //Leta igenom alla recept
            String value = entry.getValue();
            if (value.length() < input.length()) {
                continue;
            }
            boolean match = true;
            for (int i = 0; i < input.length(); i++) { //Hitta om de är rätt på rätt plats
                if (input.charAt(i) != value.charAt(i)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                searchRes.add(value); //Lägger till recept
            }
        }
        setSearchRes(searchRes);
        setArrayAdapterListPopUp();
    }

    //Getter och setter för searchRes
    public ArrayList<String> getSearchRes() {
        return searchRes;
    }

    public void setSearchRes(ArrayList<String> potMatches) { //Setter för alla sökresultat
        this.searchRes = searchRes;
    }

    public void setData(){ //Här sätter vi data, Vi kan manupilera denna för insättning av data


        data.put(0,"Taco");
        data.put(1,"Pokebowl");
        data.put(2,"Ratatouille");
        data.put(3,"Lasange");
        data.put(4,"Kycklingmiddag");
        data.put(5,"Kycklinglunch");
        data.put(6,"Lax");
        data.put(7,"Laxar");
    }

    public void setArrayAdapterListPopUp() { //Om inte tom uppdatera listPopupWiundow

        if(!searchRes.isEmpty()) {
            adapter.notifyDataSetChanged();
            listPopupWindow.show();
        } else listPopupWindow.dismiss();
    }


    @Override
    public void click(String pos) { //Click från addLogic
        System.out.println("Jag är pos vid klick "+pos);
        if (getDataId(pos)!=Integer.MAX_VALUE){
            System.out.println("--------------- Jag har klickats "  + getDataId(pos));
        }
    }

    public int getDataId(String val){//Hitta id
        int id = Integer.MAX_VALUE;
        for (int i = 0; i<data.size(); i++) {

            if (data.get(i).equals(val)){
                id = i;
                break;
            }
        }

        if (id != Integer.MAX_VALUE) {
            return id;
        } else {
            System.out.println("Value not found in the map.");
        }
        return id;
    }
}
