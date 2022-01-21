package com.example.postsapi.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.postsapi.R;
import com.example.postsapi.data.models.Post;
import com.example.postsapi.databinding.FragmentFormBinding;
import com.example.postsapi.utils.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private static final int USER_ID = 9;
    public static final int GROUP_ID = 5;
    private Post post2;

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            post2 = (Post) getArguments().getSerializable("key");
            binding.etTitle.setText(post2.getTitle());
            binding.etContent.setText(post2.getContent());
        }


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (post2 == null) {

                    String title = binding.etTitle.getText().toString();
                    String content = binding.etContent.getText().toString();
                    Post post = new Post(title, content, USER_ID, GROUP_ID);
                    App.api.createPost(post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful()) {
                                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                                navController.popBackStack();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
                } else {
                    String title = binding.etTitle.getText().toString();
                    String content = binding.etContent.getText().toString();
                    Post post = new Post(title, content, USER_ID, GROUP_ID);
                    App.api.update(post2.getId(), post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Toast.makeText(requireActivity(), "ОБНОВИЛ", Toast.LENGTH_SHORT).show();
                            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                            navController.navigate(R.id.postsFragment);
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });

                }
            }
        });

    }

}