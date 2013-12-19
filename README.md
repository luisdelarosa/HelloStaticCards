HelloStaticCards
================

Demo app that creates Static Cards in Google Glass via the GDK (XE12).

It accomplishes this by starting up a Voice Recognizer and then passing the
results to a Service called InitialService. InitialService takes the initial
text recognized, creates a static Card with that text, then insert that
static Card into the Timeline.