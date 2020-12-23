package com.example.ceshi_06.mvp.mvp.model;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OkManager_ProOkHttpClientFactory implements Factory<OkHttpClient> {
  private final OkManager module;

  public OkManager_ProOkHttpClientFactory(OkManager module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.proOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(OkManager module) {
    return new OkManager_ProOkHttpClientFactory(module);
  }
}
