package action;

public class ProductActionFactory {
    private static ProductActionFactory factory;
    
    private ProductActionFactory() {}
    public static ProductActionFactory getInstance() {
	if(factory == null) {
	    factory = new ProductActionFactory();
	}
	return factory;
    }
    
    private Action action = null;
    public Action action(String cmd) {
	if(cmd.equals("/list.do")) {
	    action = new ProductListAction("/view/admin_goods_list.jsp");
	}else if(cmd.equals("/insert.do")) {
	    action = new ProductAddAction("/list.do");
	}
	return action;
    }
    
}
