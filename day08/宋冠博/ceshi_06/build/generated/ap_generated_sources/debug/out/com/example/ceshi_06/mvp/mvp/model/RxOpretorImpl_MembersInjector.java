package com.example.ceshi_06.mvp.mvp.model;

import com.example.ceshi_06.mvp.mvp.model.api.ApiService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RxOpretorImpl_MembersInjector implements MembersInjector<RxOpretorImpl> {
  private final Provider<ApiService> mApiServiceProvider;

  public RxOpretorImpl_MembersInjector(Provider<ApiService> mApiServiceProvider) {
    assert mApiServiceProvider != null;
    this.mApiServiceProvider = mApiServiceProvider;
  }

  public static MembersInjector<RxOpretorImpl> create(Provider<ApiService> mApiServiceProvider) {
    return new RxOpretorImpl_MembersInjector(mApiServiceProvider);
  }

  @Override
  public void injectMembers(RxOpretorImpl instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mApiService = mApiServiceProvider.get();
  }

  public static void injectMApiService(
      RxOpretorImpl instance, Provider<ApiService> mApiServiceProvider) {
    instance.mApiService = mApiServiceProvider.get();
  }
}
