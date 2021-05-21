package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;
import com.cognizant.truYum.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuItemDaoCollectionImpl implements MenuItemDao{
    private static List<MenuItem> menuItemList;

    public MenuItemDaoCollectionImpl() throws ParseException {
        if(menuItemList==null){
            menuItemList=new ArrayList<MenuItem>();
            menuItemList.add(new MenuItem(1,"Sandwich",99.0f,true, DateUtil.convertToDate("15/03/2017"),"MainCourse",true));
            menuItemList.add(new MenuItem(2,"Burger",120.0f,true,DateUtil.convertToDate("23/12/2017"),"MainCourse",false));
            menuItemList.add(new MenuItem(3,"Pizza",149.0f,true,DateUtil.convertToDate("21/08/2018"),"MainCourse",false));
            menuItemList.add(new MenuItem(4,"French Fries",57.0f,false,DateUtil.convertToDate("02/07/2017"),"Starters",false));
            menuItemList.add(new MenuItem(5,"Chocolate Brownie",32.0f,true,DateUtil.convertToDate("02/22/2022"),"Dessert",true));
        }

    }

    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        return menuItemList;
    }

    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        List<MenuItem> result=new ArrayList<MenuItem>();
        Predicate<MenuItem> obj=i->{
            if (i.getDateOfLaunch().before(new Date()) && i.isActive()){
                return true;
            }
            else{
                return false;
            }
        };
        result=menuItemList.stream().filter(obj).collect(Collectors.toList());

        return result;
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        List<MenuItem> result=menuItemList.stream().filter(item ->!(item.equals(menuItem))).collect(Collectors.toList());
        result.add(menuItem);
        menuItemList=result;

    }

    @Override
    public MenuItem getMenuItem(long menuItemId) {

        return menuItemList.stream().filter(i->i.getId()==menuItemId).limit(1).collect(Collectors.toList()).get(0);
    }
}
