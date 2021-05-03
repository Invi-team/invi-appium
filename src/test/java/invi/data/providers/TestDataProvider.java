package invi.data.providers;

import invi.utils.Date;
import invi.utils.PropertiesHandler;

public abstract class TestDataProvider {
    protected Date dateUtils = new Date();
    protected PropertiesHandler propertiesHandler = new PropertiesHandler();

    public abstract Object[][] provideData();
}
