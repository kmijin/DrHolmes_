package com.pillgood.drholmes.info.disease;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pillgood.drholmes.R;
import com.pillgood.drholmes.info.pill.Pill;
import com.pillgood.drholmes.info.pill.PillAdapter;

public class InfoDiseaseActivity extends Fragment {

    View view;
    RecyclerView recyclerView;
    DiseaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_info_disease, container, false);

        recyclerView = view.findViewById(R.id.disease_list_recycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DiseaseAdapter();

        recyclerView.setAdapter(adapter);

        adapter.addItem(new Disease("인플루엔자", "노년내과", "인플루엔자는 감기 증세를 일으키는 바이러스 중 인플루엔자(influenza) 바이러스에 의해 발생하는 질환입니다. 독감이라고도 합니다."));
        adapter.addItem(new Disease("코로나-19", "감염내과", "중국 우한에서 발생한 폐렴은 신종 코로나 바이러스인 ‘2019-nCoV' 가 원인으로 알려졌습니다. 이 신종 바이러스는 2019년 말 처음 인체 감염이 확인됐다는 의미에서 '코로나-19'로 명명되었습니다."));
        adapter.addItem(new Disease("간염", "소화기내과", "간염은 간세포 조직에 염증이 생긴 것을 의미합니다."));
        adapter.addItem(new Disease("구토", "소화기내과", "구토는 위장 속의 내용물이 식도를 거쳐 입 밖으로 나오는 증상을 의미합니다. 구역은 속이 메슥거리며 토할 것 같은 느낌을 의미합니다. 구토가 발생하기 이전에 구역이 나타납니다."));
        adapter.addItem(new Disease("각막염", "안과", "각막염은 여러 가지 원인으로 인해 각막(cornea)에 염증이 생긴 상태를 의미합니다. 우리 눈의 검은자에 해당하는 부위인 각막은 눈의 창문 역할을 하는 유리처럼 투명한 조직입니다."));
        adapter.addItem(new Disease("각기병", "신경과", "각기병은 티아민이라는 비타민 B1이 부족하여 생기는 질환으로, 다리 힘이 약해지고 지각 이상(저림 등)이 생겨서 제대로 걷지 못하는 병입니다."));
        adapter.notifyDataSetChanged();

        return view;
    }
}