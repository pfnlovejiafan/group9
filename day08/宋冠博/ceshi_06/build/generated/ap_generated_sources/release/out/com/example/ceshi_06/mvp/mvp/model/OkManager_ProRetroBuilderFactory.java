package com.example.ceshi_06.mvp.mvp.model;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OkManager_ProRetroBuilderFactory implements Factory<Retrofit.Builder> {
  private final OkManager module;

  public OkManager_ProRetroBuilderFactory(OkManager module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Retrofit.Builder get() {
    return Preconditions.checkNotNull(
        module.proRetroBuilder(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit.Builder> create(OkManager module) {
    return new OkManager_ProRetroBuilderFactory(module);
  }
}
