package com.example.ceshi_06.mvp.mvp.model;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OkManager_ProBuilderFactory implements Factory<OkHttpClient.Builder> {
  private final OkManager module;

  public OkManager_ProBuilderFactory(OkManager module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient.Builder get() {
    return Preconditions.checkNotNull(
        module.proBuilder(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient.Builder> create(OkManager module) {
    return new OkManager_ProBuilderFactory(module);
  }
}
