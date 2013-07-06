package com.doma.vlado.bookshelf.inventory.impl.mock.activator;

import com.doma.vlado.bookshelf.inventory.api.BookInventory;
import com.doma.vlado.bookshelf.inventory.impl.mock.BookInventoryMockImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class BookInventoryMockImplBundleActivator implements BundleActivator {
    private ServiceRegistration reg = null;

    public void start(BundleContext context) throws Exception {
        System.out.println(
                "\nStarting Book Inventory Mock Impl");
        this.reg = context.registerService(
                BookInventory.class.getName(),
                new BookInventoryMockImpl(), null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println(
                "\nStopping Book Inventory Mock Impl");
        if (this.reg != null) {
            context.ungetService(reg.getReference());
            this.reg = null;
        }
    }
}
