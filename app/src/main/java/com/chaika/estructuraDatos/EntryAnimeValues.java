package com.chaika.estructuraDatos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Objeto utilzado en para las llamadas al API de actualizar que requieren de distintos parametros a actualizar en la cuenta.
 * Created by ricardo on 10/5/17.
 */
@Root(name = "entry",strict = false)
public class EntryAnimeValues
{
    @Element(name = "tags",required = false)
    private String tags;
    @Element(name = "times_rewatched",required = false)
    private String times_rewatched;
    @Element(name = "episode")
    private String episode;
    @Element(name = "status")
    private String status;
    @Element(name = "priority",required = false)
    private String priority;
    @Element(name = "date_start",required = false)
    private String date_start;
    @Element(name = "score",required = false)
    private String score;
    @Element(name = "rewatch_value",required = false)
    private String rewatch_value;
    @Element(name = "enable_discussion",required = false)
    private String enable_discussion;
    @Element(name = "storage_type",required = false)
    private String storage_type;
    @Element(name = "storage_value",required = false)
    private String storage_value;
    @Element(name = "date_finish",required = false)
    private String date_finish;
    @Element(name = "comments",required = false)
    private String comments;
    @Element(name = "enable_rewatching",required = false)
    private String enable_rewatching;

    public String getTags ()
    {
        return tags;
    }

    public void setTags (String tags)
    {
        this.tags = tags;
    }

    public String getTimes_rewatched ()
    {
        return times_rewatched;
    }

    public void setTimes_rewatched (String times_rewatched)
    {
        this.times_rewatched = times_rewatched;
    }

    public String getEpisode ()
    {
        return episode;
    }

    public void setEpisode (String episode)
    {
        this.episode = episode;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getPriority ()
    {
        return priority;
    }

    public void setPriority (String priority)
    {
        this.priority = priority;
    }

    public String getDate_start ()
    {
        return date_start;
    }

    public void setDate_start (String date_start)
    {
        this.date_start = date_start;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getRewatch_value ()
    {
        return rewatch_value;
    }

    public void setRewatch_value (String rewatch_value)
    {
        this.rewatch_value = rewatch_value;
    }

    public String getEnable_discussion ()
    {
        return enable_discussion;
    }

    public void setEnable_discussion (String enable_discussion)
    {
        this.enable_discussion = enable_discussion;
    }

    public String getStorage_type ()
    {
        return storage_type;
    }

    public void setStorage_type (String storage_type)
    {
        this.storage_type = storage_type;
    }

    public String getStorage_value ()
    {
        return storage_value;
    }

    public void setStorage_value (String storage_value)
    {
        this.storage_value = storage_value;
    }

    public String getDate_finish ()
    {
        return date_finish;
    }

    public void setDate_finish (String date_finish)
    {
        this.date_finish = date_finish;
    }

    public String getComments ()
    {
        return comments;
    }

    public void setComments (String comments)
    {
        this.comments = comments;
    }

    public String getEnable_rewatching ()
    {
        return enable_rewatching;
    }

    public void setEnable_rewatching (String enable_rewatching)
    {
        this.enable_rewatching = enable_rewatching;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tags = "+tags+", times_rewatched = "+times_rewatched+", episode = "+episode+", status = "+status+", priority = "+priority+", date_start = "+date_start+", score = "+score+", rewatch_value = "+rewatch_value+", enable_discussion = "+enable_discussion+", storage_type = "+storage_type+", storage_value = "+storage_value+", date_finish = "+date_finish+", comments = "+comments+", enable_rewatching = "+enable_rewatching+"]";
    }
}
