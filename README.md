WakeOnLanSender
===============

This is a PHP project who can be suefull if you have to wakeup some pc's form your local network or for the internet.
To Wake up a pc from your local network I use a little Java applet because php can't send a WakeOnLan packet to the local network.

How it work ?
=============

### WakeOnLan idea :

Wake-on-LAN is implemented using a special network message called a magic packet. The magic packet contains the MAC address of the destination computer. The listening computer waits for a magic packet addressed to it and then initiates system wake-up.

The magic packet is sent on the data link or layer 2 in the OSI model and broadcast to all NICs using the network broadcast address; the IP-address (layer 3 in the OSI model) is not used.

It is a common misconception that because Wake-on-LAN is built upon broadcast technology it can only be used within the current network subnet. While this is generally the case, there are some exceptions.

In order for Wake-on-LAN to work, parts of the network interface need to stay on. This consumes standby power, much less than normal operating power. If Wake-on-LAN is not needed, disabling it may reduce power consumption slightly while the computer is switched off but still plugged in.
Magic packet

The magic packet is a broadcast frame containing anywhere within its payload 6 bytes of all 255 (FF FF FF FF FF FF in hexadecimal), followed by sixteen repetitions of the target computer's 48-bit MAC address, for a total of 102 bytes.

Since the magic packet is only scanned for the string above, and not actually parsed by a full protocol stack, it may be sent as any network- and transport-layer protocol, although it is typically sent as a UDP datagram to port 7 or 9.

### Requirements :

A standard magic packet has the following basic requirements:

   * Requires destination computer MAC address (also may require a SecureOn password)
   * Ip adress destination or hostname
   * A port ( 9 or 7 by default )
   * Requires hardware support of Wake-On-LAN on destination computer

The Wake-on-LAN implementation is designed to be very simple and to be quickly processed by the circuitry present on the network interface card (NIC) with minimal power requirement. Because Wake-on-LAN operates below the IP protocol layer the MAC address is required and makes IP addresses and DNS names meaningless.

To work you need to have an apache2 server fully working with php5 and socket enabled in it.
If you want to use the Java Applet for your local network you have to use Java and you need to install it.



How to install
==============

if you have an access to ssh of your server you can type this line in the dorectory you want to install it.
	
	git clone git@github.com:PoxProgram/WakeOnLanSender.git
	
If you don't have ssh access to your server you can use FTP and download the lastest zip file.
Then send files to the server exept source of the java applet if you don't need them.


Licence
=======

	This is a PHP project who can be suefull if you have to wakeup some
	pc's form your local network or for the internet. 
	Copyright (C) 2013 VILLENA Guillaume

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.