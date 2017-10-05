package com.tstv.vknewsfeed.ui.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.BaseAdapter;
import com.tstv.vknewsfeed.consts.ApiConstants;
import com.tstv.vknewsfeed.event.SendCommentEventOnSubscribe;
import com.tstv.vknewsfeed.event.SendCreatedPostEventOnSubscribe;
import com.tstv.vknewsfeed.event.UploadFileEventOnSubscribe;
import com.tstv.vknewsfeed.event.UploadPhotoEventOnSubscribe;
import com.tstv.vknewsfeed.model.view.attachment.DocAttachmentViewModel;
import com.tstv.vknewsfeed.model.view.attachment.ImageAttachmentViewModel;
import com.tstv.vknewsfeed.model.view.CreatePostTextViewModel;
import com.tstv.vknewsfeed.ui.dialog.AddAttachmentDialogFragment;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDocument;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import droidninja.filepicker.FilePickerConst;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tstv on 27.09.2017.
 */


public class CreatePostActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    VKAttachments attachments;

    CreatePostTextViewModel createPostTextViewModel;


    private BaseAdapter mAdapter;


    private RecyclerView mRecyclerView;

    String mType;
    int ownerId;
    int id;

    private static final String TAG = "CreatePostActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mType = bundle.getString("type");
            ownerId = bundle.getInt("owner_id");
            id = bundle.getInt("id");
        }
        mAdapter = new BaseAdapter();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRecyclerView.setAdapter(mAdapter);

        createPostTextViewModel = new CreatePostTextViewModel();

        mAdapter.insertItem(createPostTextViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        getSupportActionBar().setTitle(R.string.new_message_title);

        attachments = new VKAttachments();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_create_post;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_attach:
                attach();
                break;
            case R.id.action_post:
                Log.d("create post", "post");
                post();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void post() {

        if (createPostTextViewModel.getMessage() == null) {
            Toast.makeText(this, R.string.add_message_text, Toast.LENGTH_LONG).show();
            return;
        }

        ObservableOnSubscribe<VKResponse> o;
        if (mType != null && mType.equals("comment")) {
            o = new SendCommentEventOnSubscribe(ownerId, id, createPostTextViewModel.getMessage(), attachments);
        } else {
            o = new SendCreatedPostEventOnSubscribe(ApiConstants.MY_GROUP_ID,
                    createPostTextViewModel.getMessage(), attachments);
        }

        Observable.create(o)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKResponse>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        Toast.makeText(CreatePostActivity.this, R.string.message_not_published, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(VKResponse response) {
                        postResult(response);
                    }
                });
    }

    private void postResult(VKResponse vkResponse) {
        Bundle conData = new Bundle();
        conData.putString("results", "Thanks Thanks");
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void attach() {
        AddAttachmentDialogFragment dialog = new AddAttachmentDialogFragment();
        dialog.show(getFragmentManager(), AddAttachmentDialogFragment.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("createpostact", "res = " + requestCode);

        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Log.d(TAG, "onActivityResult: photo pick");
                    List<String> photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                    for (String photoPath : photoPaths) {
                        ImageAttachmentViewModel photo = new ImageAttachmentViewModel(photoPath);
                        loadPhoto(photo);


                    }
                }
                break;
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    List<String> docPaths = new ArrayList<>();
                    docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));

                    for (String docPath : docPaths) {
                        File file = new File(docPath);

                        loadFile(new DocAttachmentViewModel(file));
                    }
                }
                break;
        }
    }


    void loadPhoto(final ImageAttachmentViewModel item) {

        getProgressBar().setVisibility(View.VISIBLE);

        Observable.create(new UploadPhotoEventOnSubscribe(item.getPhotoUrl()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKApiPhoto>() {
                    @Override
                    public void onComplete() {

                        getProgressBar().setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                        getProgressBar().setVisibility(View.GONE);
                        Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();

                        getProgressBar().setVisibility(View.GONE);

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(VKApiPhoto photo) {
                        attachments.add(photo);
                        mAdapter.insertItem(item);
                        Log.d("attach", "photo: " + photo.photo_130);
                        Log.d("attach", "compl");
                        Toast.makeText(CreatePostActivity.this, R.string.loading_completed, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void loadFile(final DocAttachmentViewModel docViewModel) {

        getProgressBar().setVisibility(View.VISIBLE);

        Observable.create(new UploadFileEventOnSubscribe(docViewModel.getmFile()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKApiDocument>() {
                    @Override
                    public void onComplete() {
                        getProgressBar().setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getProgressBar().setVisibility(View.GONE);

                        Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(VKApiDocument doc) {
                        attachments.add(doc);
                        Log.d("attach", "compl");

                        mAdapter.insertItem(docViewModel);
                        Toast.makeText(CreatePostActivity.this, R.string.loading_completed, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}