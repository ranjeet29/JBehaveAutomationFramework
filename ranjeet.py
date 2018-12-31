import urllib2
import urllib
import json

BUGZ_URL = "http://10.10.30.34/bugzilla/jsonrpc.cgi?"


target_milestone = ["4.1.12"]
severity = [ "CRITICAL"]
status = ["RESOLVED", "REOPENED", "NEW"]
p_data = { "target_milestone": target_milestone, "severity": severity, "status": status }
params = []
params.append(p_data)
q = {  'method': 'Bug.search', 'params': params }
q = urllib.urlencode(q)
print "q : ",q
q = q.replace('%27', '%22')
print "q" , q
resp = urllib2.urlopen(BUGZ_URL + q, timeout=30).read()
#print "resp : ", resp
#resp = parse_response(resp)
#print "resp : ",resp

