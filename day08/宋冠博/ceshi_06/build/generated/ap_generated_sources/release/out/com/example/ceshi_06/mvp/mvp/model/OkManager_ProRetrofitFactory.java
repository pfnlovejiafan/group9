package com.example.ceshi_06.mvp.mvp.model;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OkManager_ProRetrofitFactory implements Factory<Retrofit> {
  private final OkManager module;

  public OkManager_ProRetrofitFactory(OkManager module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.proRetrofit(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(OkManager module) {
    return new OkManager_ProRetrofitFactory(module);
  }
}
