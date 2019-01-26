# Makefile from RawSockets modified Thursday, June 30, 2011 11:40:35 CDT (-0500).
#
# Some parts Copyright 2004-2007 Daniel F. Savarese and
#     Copyright 2007-2009 Savarese Software Research Corporation
#     (used with permission under Apache License, Version 2.0)
#
# Copyright 2010-2011 Nick Williams
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

UNAME  := $(shell uname)
CYGWIN := $(findstring CYGWIN,$(UNAME))
DARWIN := $(findstring Darwin,$(UNAME))

CC       = g++
SHARED   = -shared
CFLAGS   = -Wall -O2 -pipe -D_REENTRANT
WINSOCK  = ws2_32
LDFLAGS  =
CPPFLAGS =
LIBNAME  = librawsockets
LIBEXTENSION = so

ifeq ($(DARWIN),Darwin)
  JAVA_INCDIR  = $(JAVA_HOME)/include
  LIBEXTENSION = jnilib
  CPPFLAGS += -I$(JAVA_INCDIR)
  LDFLAGS += -dynamiclib -prebind -single_module -framework JavaVM
  SHARED =
else
  ifeq ($(CYGWIN),CYGWIN)
    override CC += -mno-cygwin
    CPPFLAGS += -D__int64="long long"
    LDFLAGS += -Wl,--kill-at -l$(WINSOCK)
    JDK_HOME := $(shell cygpath $(JDK_HOME))
    LIBNAME      = rawsockets
    LIBEXTENSION = dll
  endif

  JAVA_INCDIR      = $(JDK_HOME)/include
  JAVA_INCDIR_PLAF = $(dir $(wildcard $(JAVA_INCDIR)/*/jni_md.h))
  CPPFLAGS += -I$(JAVA_INCDIR) -I$(JAVA_INCDIR_PLAF)
  CFLAGS += -ansi -pthread -fpic
endif

SRC := $(shell find . -name "*.cpp" -print)
OBJ := $(SRC:%.cpp=%.o)

CLEAN_EXTENSIONS = o $(LIBEXTENSION)

LIBRAWSOCKETS = $(LIBNAME).$(LIBEXTENSION)

all: $(LIBRAWSOCKETS)

%.o: %.cpp
	@echo
	@echo Target 1: Building $< to $@
	$(CC) $(CFLAGS) $(CPPFLAGS) -c $< -o $@

$(LIBRAWSOCKETS): $(OBJ)
	@echo
	@echo Target 2: Building $^ to $@
	$(CC) $(SHARED) -o $@ $^ $(LDFLAGS)

clean:
	for extension in $(CLEAN_EXTENSIONS); do \
		find . -name "*.$$extension" | xargs rm -f ; \
	done
	find . -name "*~" | xargs rm -f
