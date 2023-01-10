package cn.missfresh.module.base.common.interfaces;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public interface IShelfFun {

    public enum PageType {
        DEFAULT_TYPE(-1),
        HOME_SHELF(0),
        ADD_ON(2),
        SECOND_PRODUCT(3),
        CLASSIFY_PRODUCT(4),
        SHOPPING_CART(5),
        MINI_SHOPPING_CART(6),
        NATION_WIDE(7),
        VIP_SHELF(8),
        GROUPON_SHELF(9),
        PRODUCT_DETAIL(10),
        FIND_LIKE(12),
        ORDER_DETAIL(13),
        RECIPES_DETAIL(14);
        
        private int code;

        public static PageType valueOf(String str) {
            AppMethodBeat.i(12069, false);
            PageType pageType = (PageType) Enum.valueOf(PageType.class, str);
            AppMethodBeat.o(12069);
            return pageType;
        }

        static {
            AppMethodBeat.i(12073, false);
            AppMethodBeat.o(12073);
        }

        private PageType(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }
}
