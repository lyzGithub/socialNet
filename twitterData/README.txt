**** CITATION ****
Please cite our paper as follows, when you are using our dataset:
Lilian Weng, Filippo Menczer, and Yong-Yeol Ahn. 
Virality Prediction and Community Structure in Social Networks. 
Nature Scientific Report. 
(3)2522, 2013.



**** DATA SOURCE ****
Sampled public tweets from Twitter streaming API
 (https://dev.twitter.com/docs/streaming-apis).
Date range: March 24, 2012 to April 25, 2012.



**** NETWORKS ****
follower_gcc.anony.dat:
	相互关注的用户对。
    Format: anony.user1.ID anony.user2.ID
    Anonymized reciprocal follower network.
    Each edge is a pair of Twitter user who are following each other.
    After recovering the reciprocal follower network, 
    the giant connected component is extracted.

retweet_gcc.anony.dat:
	存在相互转发行为的的用户对以及转发总数数据
    Format: anony.user1.ID anony.user2.ID weight
    Anonymized reciprocal retweet network.
    Similarly to follower_gcc.anony.dat, 
    but instead each edge is a pair of users 
    who retweeted each other at least once during 
    our observation time window.
    Weight is the sum of how many times user1 retweeted user2 
    or user2 retweeted user1.

mention_gcc.anony.dat:
·	相互mention提及对方的用户对以及提及次数总和
    Format: anony.user1.ID anony.user2.ID weight
    Anonymized reciprocal retweet network.
    Similarly to follower_gcc.anony.dat, 
    but instead each edge is a pair of users who mentioned each other 
    at least once during our observation time window. 
    Weight is the sum of how many times user1 mentioned user2 
    or user2 mentioned user1.



**** HASHTAG SEQUENCES ****
timeline_tag.anony.dat
	hashtag 被提及的时间以及提及的用户ID序列
    Format: hashtag timestamp1,anony.user1.id timestamp2,anony.user2.id ...
    Each line is a hashtag followed by the sequence of its adopters sorted by timestamp. 
    A user is considered as an adopter of a hashtag once he/she starts using the hashtag.
    We only consider users who appear in the collected networks. 
    The timestamp is the time when we see the hashtag in the user's tweets. 
    The file includes both emergent hashtags and non-emergent ones.

timeline_tag_rt.anony.dat
	hashtag 被转发的时间戳和转发的用户的ID以及转发的来源用户的序列
    Format: hashtag timestamp1,anony.retweet_user1.id,anony.retweet_from_user1.id timestamp2,anony.retweet_user2.id,anony.tweet_from_user2.id ...
    Each line is a hashtag followed by the sequence of 
    its adopters retweeting about this hashtag from other users sorted by timestamp. 
    A "retweet_user" retweets a message containing the hashtag from a "retweet_from_user".
    We only consider users who appear in the collected networks.
    The file includes both emergent hashtags and non-emergent ones.

timeline_tag_men.anony.dat
	hashtag 被提及的时间和主动提及的adopter用户以及被提到的用户的序列
    Format: hashtag timestamp1,anony.mention_user1.id,anony.mentioned_user1.id timestamp2,anony.mention_user2.id,anony.mentioned_user2.id ...
    Each line is a hashtag followed by the sequence of its adopters mentioning other users
    in messages containing this hashtags sorted by timestamp. 
    A "mention_user" mentions a "mentioned_user" in a message with the target hashtag. 
    We only consider users who appear in the collected networks. 
    The file includes both emergent hashtags and non-emergent ones.



** Note that users in these networks and timeline sequencies 
are anonymized in the same way so that the same IDs refer to 
the same Twitter users.


Lilian Weng
weng@indiana.edu
Apr 17, 2014
