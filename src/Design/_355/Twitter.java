package Design._355;

import java.util.*;

class Tweet{
    int id;
    int time;
    Tweet next;

    public Tweet(int id, int time){
        this.id = id;
        this.time = time;
        next = null;
    }
}

class User{
    int id;
    Set<Integer> followed;
    Tweet twitter_head;

    public User(int id){
        this.id = id;
        followed = new HashSet<>();
        follow(id);
        twitter_head = null;
    }

    public void postTweet(int tweetId, int time){
        Tweet tweet = new Tweet(tweetId, time);
        tweet.next = twitter_head;
        twitter_head = tweet;
    }

    public void follow(int followeeID){
        followed.add(followeeID);
    }

    public void unfollow(int followeeId){
        if(followed.contains(followeeId))
            followed.remove(followeeId);
    }

    public Set<Integer> getFollowed(){
        return followed;
    }
}

class Twitter {

    Map<Integer, User> map;
    int timestamp;

    /** Initialize your data structure here. */
    public Twitter() {
        map = new HashMap<>();
        timestamp = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!map.containsKey(userId))
            map.put(userId, new User(userId));

        User user = map.get(userId);
        user.postTweet(tweetId, timestamp);

        timestamp ++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        if(!map.containsKey(userId))
            return result;

        PriorityQueue<Tweet> queue = new PriorityQueue<>( (t1, t2) -> (t2.time - t1.time));
        for(Integer id : map.get(userId).getFollowed()){
            User user = map.get(id);
            if(user.twitter_head != null)
                queue.add(user.twitter_head);
        }

        while(!queue.isEmpty() && result.size() < 10){
            Tweet temp = queue.poll();
            result.add(temp.id);

            if(temp.next != null)
                queue.add(temp.next);
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!map.containsKey(followerId))
            map.put(followerId, new User(followerId));

        if(!map.containsKey(followeeId))
            map.put(followeeId, new User(followeeId));

        map.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!map.containsKey(followerId) || followerId == followeeId)
            return;

        map.get(followerId).unfollow(followeeId);
    }
}
