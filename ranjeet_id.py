import urllib2
import urllib
import json

BUGZ_URL = "http://10.10.30.34/bugzilla/jsonrpc.cgi?"


def parse_response(resp):
	resp = json.loads(resp)
	bugs = resp['result']['bugs']
	bz_data = []
	for bug in bugs:
		bug_id = bug['id']
		resolvedin = bug['cf_resolve_build']
		milestone = bug['target_milestone']
		assigned_mgr = bug['cf_assigned_manager']
		bz_data.append(dict(bug_id=bug_id, resolvedin=resolvedin, milestone=assigned_mgr))
	return json.dumps(bz_data, indent=3)

id = [ "48991" ]
p_data = { "id": id }
params = []
params.append(p_data)
q = {  'method': 'Bug.search', 'params': params }
q = urllib.urlencode(q)
print "q : ",q
q = q.replace('%27', '%22')
print "q" , q
resp = urllib2.urlopen(BUGZ_URL + q, timeout=30).read()
print "resp : ", resp
resp = parse_response(resp)
print "resp : ",resp


