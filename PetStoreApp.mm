<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1531918901879" ID="ID_227311131" MODIFIED="1531918940609" TEXT="PetStore">
<node CREATED="1531918941956" ID="ID_271857143" MODIFIED="1531918953929" POSITION="right" TEXT="REST">
<node CREATED="1531918969741" ID="ID_1425938863" MODIFIED="1531918999678" TEXT="Use @PathVariable in the method to read {petId}"/>
<node CREATED="1531919017529" ID="ID_1959370219" MODIFIED="1531919050160" TEXT="@Repository should be on PetDaoImpl instead of PetDao"/>
</node>
<node CREATED="1531919799791" ID="ID_802041436" MODIFIED="1531998269706" POSITION="right" TEXT="Logging">
<node CREATED="1531919802387" ID="ID_1689983479" MODIFIED="1531919811220" TEXT="use slf4j logger"/>
<node CREATED="1531919811810" ID="ID_620001008" MODIFIED="1531919826540" TEXT="user slf4j loggerfactory"/>
<node CREATED="1531920202169" ID="ID_1635469494" MODIFIED="1531920284628" STYLE="fork" TEXT="Logback pattern">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1531998270444" ID="ID_1708263588" MODIFIED="1531998273822" POSITION="right" TEXT="Git">
<node CREATED="1531998274698" ID="ID_1368863518" MODIFIED="1531998277114" TEXT="Merging">
<node CREATED="1531998286986" ID="ID_1595208549" MODIFIED="1531998329063" TEXT="Merge code branch where master and code branch have common branch but conflicts in a same file"/>
</node>
<node CREATED="1531998277504" ID="ID_178173350" MODIFIED="1531998281736" TEXT="Stashing">
<node CREATED="1531998330833" ID="ID_746186860" MODIFIED="1531998353466" TEXT="Stash working directory without commiting for later reference"/>
</node>
<node CREATED="1531998282850" ID="ID_1219877101" MODIFIED="1531998285028" TEXT="Rebasing">
<node CREATED="1531999743024" ID="ID_501272964" MODIFIED="1531999853164" TEXT="rebase command, goes back to the common ancestor of your branch and master, then replays all the changes till the current state of the master, then applies the changes from your branch on top"/>
<node CREATED="1532000794975" ID="ID_1995232133" MODIFIED="1532000807255" TEXT="don&apos;t rebase the work that you already pushed"/>
<node CREATED="1532000812369" ID="ID_1256154746" MODIFIED="1532000821554" TEXT="rebase only local changes before pushing"/>
</node>
</node>
<node CREATED="1532007248236" ID="ID_558743661" MODIFIED="1532007251261" POSITION="right" TEXT="MySQL">
<node CREATED="1532007252141" ID="ID_1519997296" MODIFIED="1532007265739" TEXT="create schema using Model"/>
<node CREATED="1532007266450" ID="ID_228648266" MODIFIED="1532007277974" TEXT="Forward Engineer"/>
</node>
<node CREATED="1532090933804" ID="ID_184497890" MODIFIED="1532090936317" POSITION="right" TEXT="Hibernate">
<node CREATED="1532090937486" ID="ID_1334730928" MODIFIED="1532090964125" TEXT="EntityRelationShips all messed up">
<node CREATED="1532127597573" ID="ID_1485908116" MODIFIED="1532134876117" TEXT="Photo URL to be ElementCollection"/>
<node CREATED="1532134900344" ID="ID_133136918" MODIFIED="1532134910647" TEXT="Specify CollectionTable for Photo URL"/>
<node CREATED="1532134876550" ID="ID_1163699270" MODIFIED="1532134899502" TEXT="Create Embeddable PhotoURL Entity"/>
</node>
<node CREATED="1532127606904" ID="ID_1316638500" MODIFIED="1532127630577" TEXT="@Enumerated(EnumType.String) for enums storing Enum as string in DB">
<node CREATED="1532127631779" ID="ID_25118329" MODIFIED="1532127645464" TEXT="the Enum Name should match the DB"/>
</node>
<node CREATED="1532141431239" ID="ID_1447928771" MODIFIED="1532141442065" TEXT="Cannot access same entity with two sessions"/>
<node CREATED="1532182519035" ID="ID_1971932295" MODIFIED="1532182527905" TEXT="How to query and update in same transaction"/>
</node>
<node CREATED="1532091293461" ID="ID_812331146" MODIFIED="1532091295612" POSITION="left" TEXT="Issues">
<node CREATED="1532090965386" ID="ID_389908765" MODIFIED="1532090977969" TEXT="Unable to start server due to error - Could not obtain transaction-synchronized Session for current thread">
<node CREATED="1532091212343" ID="ID_1203480385" MODIFIED="1532091286289" TEXT="Solved by removing =  (exclude = HibernateJpaAutoConfiguration.class) from SpringBootApplicationAnnotation"/>
<node CREATED="1532091306637" ID="ID_1909110364" MODIFIED="1532091319991" TEXT="Happened when first integrated with Hibernate"/>
</node>
</node>
</node>
</map>
