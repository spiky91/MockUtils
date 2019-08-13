package de.marvlamp.MockUtils;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.CDIProvider;

import org.mockito.Mockito;

public class CDIContainerMockUtils {

	private static boolean isCDI_initialized = false;
	@SuppressWarnings("rawtypes")
	private static CDI cdi;

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T> void setMockOrSpy(Class<T> classToRetrieve, T objectToBeRetrieved){
		initCDIandProvider();
		Instance instance = mock(Instance.class);
		doReturn(objectToBeRetrieved).when(instance).get();
		doReturn(instance).when(cdi).select(Mockito.eq(classToRetrieve), Mockito.any(Annotation.class));
	}

	private static void initCDIandProvider() {
		if (isCDI_initialized) {
			cdi = mock(CDI.class);
			CDIProvider cdiProvider = mock(CDIProvider.class);
			doReturn(cdi).when(cdiProvider).getCDI();
			CDI.setCDIProvider(cdiProvider);
			isCDI_initialized = true;
		}
	}
}
