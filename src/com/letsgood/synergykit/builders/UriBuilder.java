package com.letsgood.synergykit.builders;

import com.letsgood.synergykit.builders.uri.Collection;
import com.letsgood.synergykit.builders.uri.Filter;
import com.letsgood.synergykit.builders.uri.InLineCount;
import com.letsgood.synergykit.builders.uri.OrderBy;
import com.letsgood.synergykit.builders.uri.RecordId;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.builders.uri.Select;
import com.letsgood.synergykit.builders.uri.Skip;
import com.letsgood.synergykit.builders.uri.Top;
import com.letsgood.synergykit.resources.SynergyKITUri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class UriBuilder {
	/* Constants */
	private static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com/v2";		
	
	/* Attributes */
	private Resource mResource = new Resource();
	private Collection mDatabase = new Collection();
	private RecordId mRecordId = new RecordId();
	private Filter mFilter = new Filter();
	private OrderBy mOrderBy = new OrderBy();
	private Top mTop = new Top();
	private Skip mSkip = new Skip();
	private Select mSelect = new Select();
	private InLineCount mInLineCount = new InLineCount();

	

	
		
	/* Resource setter */
	public UriBuilder setResource(String resource){
		mResource.setResource(resource);
		return this;
	}
	
	/* Resource URL setter */
	public UriBuilder setDatabase(String database){
		mDatabase.setCollection(database);
		return this;
	}
	
	/* Resource Id setter */
	public UriBuilder setRecordId(String recordId){
		mRecordId.setRecordId(recordId);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, String parametr){
		mFilter.setFilter(attribute, operator, parametr);;
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, int parametr){
		mFilter.setFilter(attribute, operator, parametr);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String filter){
		mFilter.setFilter(filter);
		return this;
	}
	
	/* Select setter */
	public UriBuilder setSelect(String attribute){
		mSelect.setSelect(attribute);
		return this;
	}
	
	/* Order by desc setter */
	public UriBuilder setOrderByDesc(String parameter){
		mOrderBy.setOrderByDesc(parameter);
		return this;
	}

	/* Order by asc setter */
	public UriBuilder setOrderByAsc(String parameter){
		mOrderBy.setOrderByAsc(parameter);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setTop(int top){
		mTop.setTop(top);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setSkip(int skip){
		mSkip.setSkip(skip);
		return this;
	}
	
	public UriBuilder setInLineCountEnabled(boolean enabled){
		mInLineCount.setEnabled(enabled);
		return this;
	}
	
	/* Build */
	public SynergyKITUri build(){
		String uri = new String(BASE_SYNERGYKIT_URL);
		boolean hasFilters = false;
		
		
		uri += "/" + mResource.getResource();  //set resource	
		
		if(mDatabase.getCollection()!=null)
			uri += "/" + mDatabase.getCollection();  //set collection
		
		
		if(mRecordId.getRecordId()!=null)
			uri += "/" + mRecordId.getRecordId();	//set resource id 
		
		
		if(mSelect.getSelect()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri += mSelect.getSelect();
		}
		
		//set filter
		if(mFilter.getFilter()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=mFilter.getFilter();
		}
		
		//set order by
		if(mOrderBy.getOrderBy()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=mOrderBy.getOrderBy();
		}
			
		
		//set top
		if(mTop.getTop() != null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=mTop.getTop();
		}
		
		//set skip
		if(mSkip.getSkip()!=null){
			
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=mSkip.getSkip();
		}
		
		//set inline count
		if(mInLineCount.isEnabled()){
		
			if(hasFilters==false){
				uri+="?";
				hasFilters=true;
			}
			
			uri+=mInLineCount.getInLineCount();
		}
		
		
		
		return new SynergyKITUri(uri);	
	}
}
