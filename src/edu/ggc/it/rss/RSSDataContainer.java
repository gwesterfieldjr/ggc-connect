package edu.ggc.it.rss;

import java.util.ArrayList;
import java.util.List;

import edu.ggc.it.rss.RSSEnumSets.RSSTag;
import edu.ggc.it.rss.RSSEnumSets.RSSFeed;
import android.content.Context;


/**
 * This is a container class that has a unique URL associated with it
 * 
 * 
 * @author Derek
 *
 */
public class RSSDataContainer
{
	private final RSSFeed feed;
	private List<String> titles = new ArrayList<String>();
	private List<String> links = new ArrayList<String>();
	private List<String> publishedDates = new ArrayList<String>();
	private List<String> descriptions = new ArrayList<String>();
	
	/**
	 * Constructor
	 * 
	 * @param rssFeed	the RSSFeed associated with this container
	 */
	public RSSDataContainer(RSSFeed feed)
	{
		this.feed = feed;
	}
	
	/**
	 * Add data to the appropriate ArrayList based on the passed RSSTag
	 * This method is used in RSSParser.parseRSS()
	 * 
	 * @param data		the string information added to ArrayLists
	 * @param tag		the tag associated with the passed data
	 */
	public void add(String data, RSSTag tag)
	{
		switch (tag) 
		{
			case TITLE:
				titles.add(data);
				break;
			case LINK:
				links.add(data);
				break;
			case PUBLISH_DATE:
				publishedDates.add(data);
				break;
			case DESCRIPTION:
				descriptions.add(data);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Fills the the instance of the data container object
	 * This method MUST NOT be called on the Main thread
	 * Instead use some type of multithreading utility such as AsyncTask
	 * 
	 * @param context	Context of an activity that uses RSSDataContainer
	 * 			Used for displaying Toast messages in RSSParser
	 */
	public void fill(Context context)
	{
	    RSSParser parser = new RSSParser(this, context);
	    parser.parseRSS();
	}
	
	/**
	 * @return the RSSFeed associated with this container
	 */
	public RSSFeed getRSSFeed()
	{
		return feed;
	}
	
	/**
	 * @return size of titles ArrayList
	 */
	public int getTitlesSize()
	{
		return titles.size();
	}
	
	/**
	 * empties all lists in this container
	 */
	public void clearContainer()
	{
	    titles.clear();
	    links.clear();
	    publishedDates.clear();
	    descriptions.clear();
	}
	
	/*
	 * The getters for each of the arrays
	 * Returns string at specified index
	 */
	public String getTitleAt(int index)
	{
		return titles.get(index);
	}
	
	public String getLinkAt(int index)
	{
		return links.get(index);
	}
	
	public String getDateAt(int index)
	{
		return publishedDates.get(index);
	}
	
	public String getDescriptionAt(int index)
	{
		return descriptions.get(index);
	}
}