package com;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ApplicationLauncherTest {

    @Test
    void shouldHavePrivateNoArgsConstructor() throws NoSuchMethodException {
        Constructor<ApplicationLauncher> constructor = ApplicationLauncher.class.getDeclaredConstructor();

        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    }

    @Test
    void shouldAllowInvokingPrivateConstructorForCoverage() throws NoSuchMethodException {
        Constructor<ApplicationLauncher> constructor = ApplicationLauncher.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThatNoException().isThrownBy(constructor::newInstance);
    }

    @Test
    void shouldDefineStaticVoidMainMethodWithStringArrayParameter() throws NoSuchMethodException {
        Method mainMethod = ApplicationLauncher.class.getDeclaredMethod("main", String[].class);

        assertThat(Modifier.isStatic(mainMethod.getModifiers())).isTrue();
        assertThat(mainMethod.getReturnType()).isEqualTo(void.class);
    }

}